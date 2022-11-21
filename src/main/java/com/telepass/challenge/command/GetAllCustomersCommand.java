package com.telepass.challenge.command;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCustomersCommand {

    @Autowired
    private CustomerService customerService;

    public List<CustomerModel> execute() {
        return customerService.retrieveAllCustomers();
    }
}
