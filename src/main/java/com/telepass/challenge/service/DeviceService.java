package com.telepass.challenge.service;

import com.telepass.challenge.model.DeviceId;
import com.telepass.challenge.model.DeviceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<DeviceModel> retrieveAllDevices() throws Exception;
    DeviceModel retrieveDeviceById(DeviceId uuid) throws Exception;
    DeviceModel addNewDevice(DeviceModel deviceModel) throws Exception;
    DeviceModel updateDevice(DeviceModel deviceModel) throws Exception;
    boolean deleteDevice(String fiscalCode,String deviceId) throws Exception;

}
