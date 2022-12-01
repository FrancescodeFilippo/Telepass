package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DeviceService deviceService;

    @Override
    public List<CustomerModel> retrieveAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public CustomerModel retrieveCustomerById(String fiscalCode) throws Exception {
        Optional<CustomerModel> customerModel = customerRepository.findById(fiscalCode);
        if(!customerModel.isEmpty() && customerModel.get() != null) {
            return customerModel.get();
        } else {
            return null;
        }
    }

    @Override
    public CustomerModel addNewCustomer(CustomerModel customerModel) throws Exception{
        CustomerModel customer = customerRepository.save(customerModel);
        return customer;
    }

    @Override
    public boolean updateCustomer(CustomerModel customerModel) throws Exception {
        Optional<CustomerModel> customerToUpdate = customerRepository.findById(customerModel.getFiscalCode());
        if(!customerToUpdate.isEmpty()) {
            CustomerModel updateCustomer = customerToUpdate.get();
            updateCustomer.setAddress(customerModel.getAddress());
            customerRepository.save(updateCustomer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCustomer(String fiscalCode) throws Exception {
        if(fiscalCode != null) {
            customerRepository.deleteById(fiscalCode);
        }
    }

    @Override
    public CustomerDevices getCustomerDevicesList(String fiscalCode) throws Exception {
        //retrieve customer
        CustomerModel customer = retrieveCustomerById(fiscalCode);
        if(customer != null) {
            //retrieve all devices
            List<DeviceModel> devices = deviceService.retrieveAllDevices();

            //set data customer response
            CustomerDevices customerDevices = new CustomerDevices();
            customerDevices.setFiscalCode(customer.getFiscalCode());
            customerDevices.setAddress(customer.getAddress());
            customerDevices.setSurname(customer.getSurname());
            customerDevices.setName(customer.getName());

            //find customer device
            List<DeviceModel> customerDevicesList = new ArrayList<>();
            for (DeviceModel device : devices) {
                if (device.getDeviceId().getFiscalCode().equalsIgnoreCase(customer.getFiscalCode())) {
                    customerDevicesList.add(device);
                }
            }

            //set device and return response
            customerDevices.setDeviceList(customerDevicesList);
            return customerDevices;
        } else {
            return null;
        }
    }

}
