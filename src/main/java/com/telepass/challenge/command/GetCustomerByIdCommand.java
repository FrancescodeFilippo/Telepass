package com.telepass.challenge.command;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GetCustomerByIdCommand {

    //CLASS VARIABLES
    private String fiscalCode;
    @Autowired
    private CustomerService customerService;

    //CONSTRUCTOR
    public GetCustomerByIdCommand() {}
    public GetCustomerByIdCommand(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    //METHODS
    public CustomerModel execute() throws Exception{
        if(fiscalCode != null) {
            return customerService.retrieveCustomerById(fiscalCode);
        }
        throw new Exception("Input Param is null!");
    }

}
