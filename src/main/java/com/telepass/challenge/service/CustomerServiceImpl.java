package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerModel> retrieveAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public CustomerModel retrieveCustomerById(String fiscalCode) throws Exception {
        Optional<CustomerModel> customerModel = customerRepository.findById(fiscalCode);
        return customerModel.get();
    }

    @Override
    public CustomerModel addNewCustomer(CustomerModel customerModel) throws Exception{
        CustomerModel customer = customerRepository.save(customerModel);
        return customer;
    }

    @Override
    public void updateCustomer(CustomerModel customerModel) throws Exception {
        CustomerModel customerToUpdate = customerRepository.findById(customerModel.getFiscalCode()).get();
        if(customerToUpdate != null) {
            customerRepository.save(customerModel);
        }
    }

    @Override
    public void deleteCustomer(String fiscalCode) throws Exception {
        if(fiscalCode != null) {
            customerRepository.deleteById(fiscalCode);
        }
    }

}
