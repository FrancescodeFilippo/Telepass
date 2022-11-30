package com.telepass.challenge.service;

import com.telepass.challenge.model.DeviceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<DeviceModel> retrieveAllDevices() throws Exception;
    DeviceModel retrieveDeviceById(String uuid) throws Exception;
    DeviceModel addNewDevice(DeviceModel deviceModel) throws Exception;
    void updateDevice(DeviceModel deviceModel) throws Exception;
    void deleteDevice(String fiscalCode,String deviceId) throws Exception;
//    void deleteDevice(String fiscalCode) throws Exception;

}
