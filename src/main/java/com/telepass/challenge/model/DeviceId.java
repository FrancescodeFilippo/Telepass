package com.telepass.challenge.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class DeviceId implements Serializable {
    private String uuid;
    private String fiscalCode;

    public DeviceId() {}
    public DeviceId(String uuid, String fiscalCode) {
        this.uuid = uuid;
        this.fiscalCode = fiscalCode;
    }
}
