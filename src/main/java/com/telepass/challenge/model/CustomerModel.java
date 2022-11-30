package com.telepass.challenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Customer")
@Getter
@Setter
public class CustomerModel implements Serializable {

    @Id
    @Column(name="fiscal_code")
    private String fiscalCode;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="address")
    private String address;

}
