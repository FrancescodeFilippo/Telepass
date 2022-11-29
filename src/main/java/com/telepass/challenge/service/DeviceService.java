package com.telepass.challenge.service;

import com.telepass.challenge.model.DeviceModel;

import java.util.List;

public interface DeviceService {

    List<DeviceModel> retrieveAllDevices() throws Exception;
    DeviceModel retrieveDeviceById(String fiscalCode) throws Exception;
    DeviceModel addNewDevice(DeviceModel deviceModel) throws Exception;
    void updateDevice(DeviceModel deviceModel) throws Exception;
    void deleteDevice(String fiscalCode) throws Exception;

}
