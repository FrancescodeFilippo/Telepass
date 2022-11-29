package com.telepass.challenge.command.device;

import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class GetAllDevicesCommand {

    //CLASS VARIABLES
    @Autowired
    private DeviceService deviceService;

    //METHODS
    public List<DeviceModel> execute() throws Exception{
        return deviceService.retrieveAllDevices();
    }
}
