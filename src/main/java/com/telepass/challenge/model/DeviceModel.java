package com.telepass.challenge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Device")
@Getter
@Setter
public class DeviceModel implements Serializable {

    @EmbeddedId
    private DeviceId deviceId;
    @Column(name="state")
    private String state;
    public DeviceModel() {}

}
