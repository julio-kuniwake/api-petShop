package com.kuniwake.petShop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;

    public Address() {
    }

    public Address(String cep, String state, String city, String district, String street, int number) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.number = number;
    }

}
