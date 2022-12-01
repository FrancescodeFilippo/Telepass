package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerModel> retrieveAllCustomers() throws Exception;
    CustomerModel retrieveCustomerById(String fiscalCode) throws Exception;
    CustomerModel addNewCustomer(CustomerModel customerModel) throws Exception;
    boolean updateCustomer(CustomerModel customerModel) throws Exception;
    void deleteCustomer(String fiscalCode) throws Exception;
    CustomerDevices getCustomerDevicesList(String fiscalCode) throws Exception;

}
