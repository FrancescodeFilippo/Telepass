package com.telepass.challenge.command;

import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DeleteCustomerCommand {

    //CLASS VARIABLES
    private String fiscalCode;
    @Autowired
    private CustomerService customerService;

    //CONSTRUCTOR
    public DeleteCustomerCommand() {}
    public DeleteCustomerCommand(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    //METHODS
    public void execute() throws Exception {
        if(fiscalCode != null) {
            customerService.deleteCustomer(this.fiscalCode);
        } else {
            throw new Exception("Input Param is null!");
        }
    }
}
