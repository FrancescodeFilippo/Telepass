package com.telepass.challenge.controller;

import com.telepass.challenge.model.CustomerModel;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.repository.CustomerRepository;
import com.telepass.challenge.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @Autowired
    private DeviceRepository deviceRepository;

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
        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/device");
    }

    //Test api get device By uuid
    @Test
    public void getDeviceIntegrationTest() {

        DeviceModel deviceModel = restTemplate.getForObject(baseUrl.concat("/get/{uuid}"), DeviceModel.class, "123");
        assertAll(
                () -> assertNotNull(deviceModel),
                () -> assertEquals("123", deviceModel.getUuid()),
                () -> assertEquals("ACTIVE", deviceModel.getState())
        );
    }

    //Test api get device list
    @Test
    public void getAllDevicesIntegrationTest() {

        ResponseEntity<DeviceModel[]> deviceModel = restTemplate.getForEntity(baseUrl.concat("/getAll"), DeviceModel[].class);
        List<DeviceModel> deviceModelList = Arrays.stream(deviceModel.getBody()).toList();
        for (DeviceModel device : deviceModelList) {
            assertAll(
                    () -> assertNotNull(device)
            );
        }
    }

    //Test add new Device
    @Test
    public void createDeviceIntegrationTest() {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setUuid("1234");
        deviceModel.setState("ACTIVE");
        DeviceModel device = restTemplate.postForObject(baseUrl.concat("/create"),deviceModel, DeviceModel.class);
        assertAll(
                () -> assertNotNull(device),
                () -> assertEquals("1234", device.getUuid()),
                () -> assertEquals("ACTIVE", device.getState())
        );
    }

    //Test update device
    @Test
    public void updateDeviceIntegrationTest() {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setUuid("123");
        deviceModel.setState("LOST");
        restTemplate.put(baseUrl.concat("/update"), deviceModel);
        DeviceModel deviceUpdated = deviceRepository.findById("123").get();
        assertAll(
                () -> assertNotNull(deviceUpdated),
                () -> assertEquals("123", deviceUpdated.getUuid()),
                () -> assertEquals("LOST", deviceUpdated.getState())
        );
    }

    //Test api delete Device By Id
    @Test
    public void deleteDeviceIntegrationTest() {

        int count = deviceRepository.findAll().size();
        restTemplate.delete(baseUrl.concat("/delete/{uuid}"),"123");

        int countAfterDelete = deviceRepository.findAll().size();
        if(count != 0) {
            assertEquals(count-1, countAfterDelete);
        } else {
            assertEquals(count, countAfterDelete);
        }
    }

}
