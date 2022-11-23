package com.telepass.challenge.command;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class GetAllCustomersCommand {

    //CLASS VARIABLES
    @Autowired
    private CustomerService customerService;

    //METHODS
    public List<CustomerModel> execute() throws Exception{
        return customerService.retrieveAllCustomers();
    }
}
