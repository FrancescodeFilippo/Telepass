package com.telepass.challenge.command.device;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.CustomerService;
import com.telepass.challenge.service.DeviceService;
import com.telepass.challenge.utils.DeviceStateEnum;
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
        CustomerModel customerModel = getCustomer();
        if(customerModel != null) {
            //retrieve Customer devices List
            CustomerDevices customerDevices = getCustomerDevices(customerModel);
            //if customer has less of 2 device add new device
            if (customerModel != null && customerDevices.getDeviceList().size() < maxCustomerDeviceNumber) {
                //check if device to add is new device and has valid state
                boolean isNewDevice = checkIsNewDevice(customerDevices);
                boolean hasValidState = checkStateOfNewDevice();
                if(isNewDevice && hasValidState) {
                    return deviceService.addNewDevice(this.deviceModel);
                } else {
                    throw new Exception("Device already exist or Invalid State!");
                }
            } else {
                throw new Exception("Customer has 2 or more device!");
            }
        } else {
            return null;
        }
    }

    private CustomerModel getCustomer() throws Exception{
        return customerService.retrieveCustomerById(deviceModel.getDeviceId().getFiscalCode());
    }

    private CustomerDevices getCustomerDevices(CustomerModel customerModel) throws Exception {
        return customerService.getCustomerDevicesList(customerModel.getFiscalCode());
    }

    private boolean checkIsNewDevice(CustomerDevices customerDevices) throws Exception {
        for (DeviceModel customerDevice: customerDevices.getDeviceList()) {
            if(customerDevice.getDeviceId().getUuid().equalsIgnoreCase(deviceModel.getDeviceId().getUuid())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkStateOfNewDevice() throws Exception{
        try {
            DeviceStateEnum.valueOf(deviceModel.getState()).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
