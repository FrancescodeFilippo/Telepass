package com.telepass.challenge.command.device;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateDeviceCommand {

    //CLASS VARIABLES
    private DeviceModel deviceModel;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public CreateDeviceCommand() {}
    public CreateDeviceCommand(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    //METHODS
    public DeviceModel execute() throws Exception {
        if(deviceModel != null) {
            return deviceService.addNewDevice(this.deviceModel);
        }
        throw new Exception("Input Param is null!");
    }
}
