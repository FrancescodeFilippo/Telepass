package com.telepass.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Device")
@Getter
@Setter
public class DeviceModel {

    @Id
    @Column(name="uuid")
    private String uuid;
    @Column(name="state")
    private String state;

}
