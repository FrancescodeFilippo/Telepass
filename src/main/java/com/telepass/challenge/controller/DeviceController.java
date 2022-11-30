package com.telepass.challenge.controller;

import com.telepass.challenge.command.device.*;
import com.telepass.challenge.model.DeviceModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "device/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DeviceController {

    @Autowired
    private BeanFactory beanFactory;

    //Retrieve all devices from db
    @GetMapping("/getAll")
    public ResponseEntity<List<DeviceModel>> getAllDevice() {

        try {
            GetAllDevicesCommand getAllDevicesCommand = beanFactory.getBean(GetAllDevicesCommand.class);
            List<DeviceModel> deviceModelList = getAllDevicesCommand.execute();
            return new ResponseEntity<>(deviceModelList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Search device by id
    @GetMapping("/get/{uuid}")
    public ResponseEntity<DeviceModel> getDeviceById(@PathVariable("uuid") String uuid) {

        try {
            GetDeviceByIdCommand getDeviceByIdCommand = beanFactory.getBean(GetDeviceByIdCommand.class, uuid);
            DeviceModel device = getDeviceByIdCommand.execute();
            if(device != null) {
                return new ResponseEntity<>(device,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Save new customer on db
    @PostMapping("/create")
    public ResponseEntity<DeviceModel> createDevice(@RequestBody DeviceModel deviceModel) {
        try {
            CreateDeviceCommand createDeviceCommand = beanFactory.getBean(CreateDeviceCommand.class, deviceModel);
            DeviceModel device = createDeviceCommand.execute();
            if(device != null) {
                return ResponseEntity.ok(device);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.noContent().eTag(e.getMessage()).build();
        }
    }

    //Update customer by id
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateDevice(@RequestBody DeviceModel deviceModel) {
        try {
            UpdateDeviceCommand updateDeviceCommand = beanFactory.getBean(UpdateDeviceCommand.class, deviceModel);
            updateDeviceCommand.execute();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete customer from db
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteDevice(@RequestBody DeviceModel deviceModel) {
        try {
            DeleteDeviceCommand deleteDeviceCommand = beanFactory.getBean(DeleteDeviceCommand.class, deviceModel);
            deleteDeviceCommand.execute();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
