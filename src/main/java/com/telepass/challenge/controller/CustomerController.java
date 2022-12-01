package com.telepass.challenge.controller;

import com.telepass.challenge.command.customer.*;
import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.CustomerModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "customer/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    @Autowired
    private BeanFactory beanFactory;

    //Retrieve all customers from db
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerModel>> getAllCustomer() {

        try {
            GetAllCustomersCommand getAllCustomersCommand = beanFactory.getBean(GetAllCustomersCommand.class);
            List<CustomerModel> customers = getAllCustomersCommand.execute();
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Search customer by id
    @GetMapping("/getCustomer/{fiscalCode}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("fiscalCode") String fiscalCode) {

        try {
            GetCustomerByIdCommand getCustomerByIdCommand = beanFactory.getBean(GetCustomerByIdCommand.class, fiscalCode);
            CustomerModel customer = getCustomerByIdCommand.execute();
            if(customer != null) {
                return new ResponseEntity<>(customer,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Save new customer on db
    @PostMapping("/create")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        try {
            CreateCustomerCommand createCustomerCommand = beanFactory.getBean(CreateCustomerCommand.class, customerModel);
            CustomerModel customer = createCustomerCommand.execute();
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update customer by id
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateCustomer(@RequestBody CustomerModel customerModel) {
        try {
            UpdateCustomerCommand updateCustomerCommand = beanFactory.getBean(UpdateCustomerCommand.class, customerModel);
            boolean updated = updateCustomerCommand.execute();
            if(updated) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete customer from db
    @DeleteMapping("/delete/{fiscalCode}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("fiscalCode") String fiscalCode) {
        try {
            DeleteCustomerCommand deleteCustomerCommand = beanFactory.getBean(DeleteCustomerCommand.class, fiscalCode);
            deleteCustomerCommand.execute();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getDevice/{fiscalCode}")
    public ResponseEntity<CustomerDevices> getCustomerDevices(@PathVariable("fiscalCode") String fiscalCode) {

        try {
            GetCustomerDevicesCommand getCustomerDevicesCommand = beanFactory.getBean(GetCustomerDevicesCommand.class, fiscalCode);
            CustomerDevices customerDevices = getCustomerDevicesCommand.execute();
            if(customerDevices != null) {
                return new ResponseEntity<>(customerDevices,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
