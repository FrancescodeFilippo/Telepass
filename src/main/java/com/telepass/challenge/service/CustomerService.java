package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerModel;
import java.util.List;

public interface CustomerService {

    List<CustomerModel> retrieveAllCustomers();
    CustomerModel retrieveCustomerById(String fiscalCode);
}
