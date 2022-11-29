package com.telepass.challenge.command.device;

import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DeleteDeviceCommand {

    //CLASS VARIABLES
    private String uuid;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public DeleteDeviceCommand() {}
    public DeleteDeviceCommand(String uuid) {
        this.uuid = uuid;
    }

    //METHODS
    public void execute() throws Exception {
        if(uuid != null) {
            deviceService.deleteDevice(this.uuid);
        } else {
            throw new Exception("Input Param is null!");
        }
    }
}
