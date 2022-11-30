package com.telepass.challenge.command.customer;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.CustomerService;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class GetCustomerDevicesCommand {

    //CLASS VARIABLES
    private String fiscalCode;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeviceService deviceService;

    //CONSTRUCTOR
    public GetCustomerDevicesCommand() {}
    public GetCustomerDevicesCommand(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    //METHODS
    public CustomerDevices execute() throws Exception{
        if(fiscalCode != null) {
            return customerService.getCustomerDevicesList(fiscalCode);
        } else {
            throw new Exception("Input param is null!");
        }
    }

}
