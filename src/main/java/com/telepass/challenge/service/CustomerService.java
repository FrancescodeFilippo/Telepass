package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerModel;
import java.util.List;

public interface CustomerService {

    List<CustomerModel> retrieveAllCustomers() throws Exception;
    CustomerModel retrieveCustomerById(String fiscalCode) throws Exception;
    CustomerModel addNewCustomer(CustomerModel customerModel) throws Exception;
    void updateCustomer(CustomerModel customerModel) throws Exception;
    void deleteCustomer(String fiscalCode) throws Exception;

}
