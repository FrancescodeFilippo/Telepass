package com.telepass.challenge.controller;

import com.telepass.challenge.command.device.DeleteDeviceCommand;
import com.telepass.challenge.model.DeviceId;
import com.telepass.challenge.model.DeviceModel;
import com.telepass.challenge.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private BeanFactory beanFactory;

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

        DeviceModel deviceModel = restTemplate.getForObject(baseUrl.concat("/get/{fiscalCode}/{uuid}"), DeviceModel.class, "ABC123","456");
        assertAll(
                () -> assertNotNull(deviceModel),
                () -> assertEquals("456", deviceModel.getDeviceId().getUuid()),
                () -> assertEquals("INACTIVE", deviceModel.getState()),
                () -> assertEquals("ABC123", deviceModel.getDeviceId().getFiscalCode())
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
        DeviceId deviceId = new DeviceId("5","CBA321");
        deviceModel.setDeviceId(deviceId);
        deviceModel.setState("ACTIVE");
        DeviceModel device = restTemplate.postForObject(baseUrl.concat("/create"),deviceModel, DeviceModel.class);
        assertAll(
                () -> assertNotNull(device),
                () -> assertEquals("5", device.getDeviceId().getUuid()),
                () -> assertEquals("ACTIVE", device.getState()),
                () -> assertEquals("CBA321", device.getDeviceId().getFiscalCode())
        );
    }

    //Test update device
    @Test
    public void updateDeviceIntegrationTest() {
        DeviceModel deviceModel = new DeviceModel();
        DeviceId deviceId = new DeviceId("678","CBA321");
        deviceModel.setDeviceId(deviceId);
        deviceModel.setState("LOST");
        restTemplate.put(baseUrl.concat("/update"), deviceModel);
        DeviceModel deviceUpdated = deviceRepository.findById(deviceId).get();
        assertAll(
                () -> assertNotNull(deviceUpdated),
                () -> assertEquals("678", deviceUpdated.getDeviceId().getUuid()),
                () -> assertEquals("LOST", deviceUpdated.getState()),
                () -> assertEquals("CBA321", deviceUpdated.getDeviceId().getFiscalCode())

        );
    }

    //Test api delete Device By Id
    @Test
    public void deleteDeviceIntegrationTest() throws Exception{

        int count = deviceRepository.findAll().size();
        DeleteDeviceCommand deleteDeviceCommand = beanFactory.getBean(DeleteDeviceCommand.class, "ABC123","123");
        deleteDeviceCommand.execute();
        int countAfterDelete = deviceRepository.findAll().size();
        if(count != 0) {
            assertEquals(count-1, countAfterDelete);
        } else {
            assertEquals(count, countAfterDelete);
        }
    }

}
