package com.telepass.challenge.unit.test;

import com.telepass.challenge.model.CustomerDevices;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTest {

    @Autowired
    CustomerService customerService;

    //test get customer's devices
    @Test
    public void getCustomerDevicesList() throws Exception{
        String fiscalCode = "CDE456";
        CustomerDevices devices = customerService.getCustomerDevicesList(fiscalCode);
        assertAll(
                () -> assertNotNull(devices),
                () -> assertEquals("CDE456", devices.getFiscalCode()),
                () -> assertEquals("Giovanni", devices.getName()),
                () -> assertEquals("Rossi", devices.getSurname())
                );

        for (DeviceModel device: devices.getDeviceList()) {
            assertAll(
                    () -> assertNotNull(device)
            );
        }
    }
}
