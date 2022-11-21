package com.telepass.challenge.command;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetCustomerByIdCommand {

    private String fiscalCode;

    public GetCustomerByIdCommand() {}

    public GetCustomerByIdCommand(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Autowired
    private CustomerService customerService;

    public CustomerModel execute() throws Exception{
        if(fiscalCode != null) {
            return customerService.retrieveCustomerById(fiscalCode);
        }
        throw new Exception("Input Param is null!");
    }

}
