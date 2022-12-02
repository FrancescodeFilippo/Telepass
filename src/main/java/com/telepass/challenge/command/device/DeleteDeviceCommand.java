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
    private String fiscalCode;
    private String uuid;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public DeleteDeviceCommand() {}
    public DeleteDeviceCommand(String fiscalCode, String uuid) {
        this.fiscalCode = fiscalCode;
        this.uuid = uuid;
    }

    //METHODS
    public boolean execute() throws Exception {
        if(fiscalCode != null && uuid != null) {
            return deviceService.deleteDevice(fiscalCode, uuid);
        } else {
            throw new Exception("Input Param is null!");
        }
    }
}
