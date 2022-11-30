package com.telepass.challenge.service;

import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<DeviceModel> retrieveAllDevices() throws Exception {
        return deviceRepository.findAll();
    }

    @Override
    public DeviceModel retrieveDeviceById(String uuid) throws Exception {
        Optional<DeviceModel> deviceModel = deviceRepository.findById(uuid);
        if(!deviceModel.isEmpty() && deviceModel.get() != null) {
            return deviceModel.get();
        } else {
            return null;
        }
    }

    @Override
    public DeviceModel addNewDevice(DeviceModel deviceModel) throws Exception {
        DeviceModel device = deviceRepository.save(deviceModel);
        return device;
    }

    @Override
    public void updateDevice(DeviceModel deviceModel) throws Exception {
        DeviceModel deviceToUpdate = deviceRepository.findById(deviceModel.getUuid()).get();
        if(deviceToUpdate != null) {
            deviceToUpdate.setState(deviceModel.getState());
            deviceRepository.save(deviceToUpdate);
        }
    }

    @Override
    public void deleteDevice(String uuid) throws Exception {
        if(uuid != null) {
            deviceRepository.deleteById(uuid);
        }
    }
}
