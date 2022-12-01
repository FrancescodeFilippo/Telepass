package com.telepass.challenge.command.device;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.CustomerService;
import com.telepass.challenge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateDeviceCommand {

    //CLASS VARIABLES
    private DeviceModel deviceModel;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CustomerService customerService;

    @Value("${max.customer.device.number}")
    private Integer maxCustomerDeviceNumber;

    //CONSTRUCTOR
    public CreateDeviceCommand() {}
    public CreateDeviceCommand(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    //METHODS
    public DeviceModel execute() throws Exception {
        //retrieve customer
        CustomerModel customerModel = customerService.retrieveCustomerById(deviceModel.getFiscalCode());
        if(customerModel != null) {
            //retrieve Customer devices List
            CustomerDevices customerDevices = customerService.getCustomerDevicesList(customerModel.getFiscalCode());
            //if customer has less of 2 device add new device
            if (customerModel != null && customerDevices.getDeviceList().size() < maxCustomerDeviceNumber) {
                return deviceService.addNewDevice(this.deviceModel);
            } else {
                throw new Exception("Customer has 2 or more device!");
            }
        } else {
            return null;
        }
    }
}
