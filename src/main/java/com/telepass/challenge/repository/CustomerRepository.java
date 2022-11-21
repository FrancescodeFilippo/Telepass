package com.telepass.challenge.repository;

import com.telepass.challenge.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

}
