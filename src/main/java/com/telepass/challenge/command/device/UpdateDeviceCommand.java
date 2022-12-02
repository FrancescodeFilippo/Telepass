package com.telepass.challenge.command.device;

import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.DeviceService;
import com.telepass.challenge.utils.DeviceStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpdateDeviceCommand {

    //CLASS VARIABLES
    private DeviceModel deviceModel;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public UpdateDeviceCommand() {}
    public UpdateDeviceCommand(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    //METHODS
    public DeviceModel execute() throws Exception {
        if(deviceModel != null) {
            try{
                DeviceStateEnum.valueOf(deviceModel.getState());
                return deviceService.updateDevice(this.deviceModel);
            }catch (Exception e) {
                throw new Exception("Invalid State!");
            }
        } else {
            throw new Exception("Input Param is null!");
        }
    }
}
