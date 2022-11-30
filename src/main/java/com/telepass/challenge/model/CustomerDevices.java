package com.telepass.challenge.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDevices {

    private String fiscalCode;
    private String name;
    private String surname;
    private String address;
    List<DeviceModel> deviceList;

}
