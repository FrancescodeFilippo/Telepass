package com.telepass.challenge.command.device;

import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DeleteDeviceCommand {

    //CLASS VARIABLES
    private DeviceModel deviceModel;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public DeleteDeviceCommand() {}
    public DeleteDeviceCommand(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    //METHODS
    public void execute() throws Exception {
        if(deviceModel != null) {
            deviceService.deleteDevice(deviceModel.getFiscalCode(), deviceModel.getUuid());
        } else {
            throw new Exception("Input Param is null!");
        }
    }
}
