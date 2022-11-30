package com.telepass.challenge.command.customer;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CreateCustomerCommand {

    //CLASS VARIABLES
    private CustomerModel customerModel;
    @Autowired
    private CustomerService customerService;

    //CONSTRUCTOR
    public CreateCustomerCommand() {}
    public CreateCustomerCommand(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    //METHODS
    public CustomerModel execute() throws Exception {
        if(customerModel != null) {
            return customerService.addNewCustomer(this.customerModel);
        }
        throw new Exception("Input Param is null!");
    }
}
