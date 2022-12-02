package com.telepass.challenge.service;

import com.telepass.challenge.model.DeviceId;
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
    public DeviceModel retrieveDeviceById(DeviceId deviceId) throws Exception {
        Optional<DeviceModel> deviceModel = deviceRepository.findById(deviceId);
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
    public DeviceModel updateDevice(DeviceModel deviceModel) throws Exception {
        DeviceModel deviceToUpdate = retrieveDeviceById(deviceModel.getDeviceId());
        if(deviceToUpdate != null) {
            deviceToUpdate.setState(deviceModel.getState());
            return deviceRepository.save(deviceToUpdate);
        }
        return null;
    }

    @Override
    public boolean deleteDevice(String fiscalCode,String uuid) throws Exception {
        DeviceId deviceId = new DeviceId(uuid,fiscalCode);
        DeviceModel deviceToDelete = retrieveDeviceById(deviceId);
        if(deviceToDelete != null) {
            deviceRepository.deleteDevices(uuid,fiscalCode);
            return true;
        } else {
            return false;
        }
    }


}
