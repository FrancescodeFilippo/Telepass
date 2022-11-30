package com.telepass.challenge.command.device;

import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GetDeviceByIdCommand {

    //CLASS VARIABLES
    private String uuid;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public GetDeviceByIdCommand() {}
    public GetDeviceByIdCommand(String uuid) {
        this.uuid = uuid;
    }

    //METHODS
    public DeviceModel execute() throws Exception{
        if(uuid != null) {
            return deviceService.retrieveDeviceById(uuid);
        } else {
            throw new Exception("Input Param is null");
        }
    }

}
