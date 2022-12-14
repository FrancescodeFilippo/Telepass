package com.telepass.challenge.command.customer;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpdateCustomerCommand {

    //CLASS VARIABLES
    private CustomerModel customerModel;
    @Autowired
    private CustomerService customerService;

    //CONSTRUCTOR
    public UpdateCustomerCommand() {}
    public UpdateCustomerCommand(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    //METHODS
    public boolean execute() throws Exception {
        if(customerModel != null && customerModel.getFiscalCode()!= null) {
            return customerService.updateCustomer(this.customerModel);
        } else {
            return false;
        }
    }
}
