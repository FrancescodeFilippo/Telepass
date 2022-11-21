package com.telepass.challenge.service;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerModel> retrieveAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerModel retrieveCustomerById(String fiscalCode) {
        Optional<CustomerModel> customerModel = customerRepository.findById(fiscalCode);
        return customerModel.get();
    }

}
