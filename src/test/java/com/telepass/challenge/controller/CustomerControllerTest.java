package com.telepass.challenge.controller;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/customer");
    }

    //Test api getCustomerById
    @Test
    public void getCustomerIntegrationTest() {

        CustomerModel customerModel = restTemplate.getForObject(baseUrl.concat("/getCustomer/{id}"), CustomerModel.class, "ABC123");
        assertAll(
                () -> assertNotNull(customerModel),
                () -> assertEquals("TName", customerModel.getName()),
                () -> assertEquals("TSurname", customerModel.getSurname()),
                () -> assertEquals("ABC123", customerModel.getFiscalCode()),
                () -> assertEquals("Via dei Mille", customerModel.getAddress())
        );
    }

    //Test api getCustomerList
    @Test
    public void getAllCustomerIntegrationTest() {

        ResponseEntity<CustomerModel[]> customerModel = restTemplate.getForEntity(baseUrl.concat("/getAll"), CustomerModel[].class);
        List<CustomerModel> customerModelList = Arrays.stream(customerModel.getBody()).toList();
        for (CustomerModel customer : customerModelList) {
            assertAll(
                    () -> assertNotNull(customer)
            );
        }
    }

    //Test add new Customer
    @Test
    public void createCustomerIntegrationTest() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setFiscalCode("ABCDE12345");
        customerModel.setName("TestName");
        customerModel.setSurname("TestSurname");
        customerModel.setAddress("TestAddress");
        CustomerModel customerCreated = restTemplate.postForObject(baseUrl.concat("/create"),customerModel, CustomerModel.class);
        assertAll(
                () -> assertNotNull(customerCreated),
                () -> assertEquals("TestName", customerCreated.getName()),
                () -> assertEquals("TestSurname", customerCreated.getSurname()),
                () -> assertEquals("ABCDE12345", customerCreated.getFiscalCode()),
                () -> assertEquals("TestAddress", customerCreated.getAddress())
        );
    }

    //Test update customer
    @Test
    public void updateCustomerIntegrationTest() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setFiscalCode("EDC654");
        customerModel.setAddress("TestAddressUpdate");
        restTemplate.put(baseUrl.concat("/update"),customerModel);

        CustomerModel customerUpdated = customerRepository.findById("EDC654").get();
        assertAll(
                () -> assertNotNull(customerUpdated),
                () -> assertEquals("Paolo", customerUpdated.getName()),
                () -> assertEquals("Rossi", customerUpdated.getSurname()),
                () -> assertEquals("EDC654", customerUpdated.getFiscalCode()),
                () -> assertEquals("TestAddressUpdate", customerUpdated.getAddress())
        );
    }

    //Test api delete Customer By Id
    @Test
    public void deleteCustomerIntegrationTest() {

        int count = customerRepository.findAll().size();
        restTemplate.delete(baseUrl.concat("/delete/{fiscalCode}"),"ABC123");

        int countAfterDelete = customerRepository.findAll().size();
        if(count != 0) {
            assertEquals(count-1, countAfterDelete);
        } else {
            assertEquals(count, countAfterDelete);
        }
    }

}
