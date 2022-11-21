package com.telepass.challenge.controller;

import com.telepass.challenge.command.GetAllCustomersCommand;
import com.telepass.challenge.command.GetCustomerByIdCommand;
import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "customer/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GetAllCustomersCommand getAllCustomersCommand;

    @Autowired
    private GetCustomerByIdCommand getCustomerByIdCommand;

    //Retrieve all customers from db
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerModel>> getAllCustomer() throws Exception  {

        GetAllCustomersCommand getAllCustomersCommand = beanFactory.getBean(GetAllCustomersCommand.class);
        List<CustomerModel> customers = getAllCustomersCommand.execute();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    //Search customer by id
    @GetMapping("/getCustomer/{fiscalCode}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("fiscalCode") String fiscalCode) throws Exception  {

        GetCustomerByIdCommand getCustomerByIdCommand = beanFactory.getBean(GetCustomerByIdCommand.class, fiscalCode);
        CustomerModel customer = getCustomerByIdCommand.execute();
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    //Save new customer on db
    @PostMapping("/create")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel tutorial) {
        try {
            //TODO spostare in command e creare service
            CustomerModel customer = customerRepository
                    .save(new CustomerModel());
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update customer by id
    @PutMapping("/update")
    public ResponseEntity<CustomerModel> updateCustomer(@RequestBody CustomerModel tutorial) {
        try {
            //TODO spostare in command e creare service
            CustomerModel customer = customerRepository
                    .save(new CustomerModel());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete customer from db
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteCustomer(@RequestBody CustomerModel customerModel) {
        try {
            //TODO spostare in command e creare service
            customerRepository.delete(customerModel);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
