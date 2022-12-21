package com.kuniwake.petShop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pet")
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private LocalDate age;
    @ManyToOne // Muitos 'Pet' para um 'Client'
    private Client client;

    public Pet() {
    }

    public Pet(String name, String breed, LocalDate age, Client client) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.client = client;
    }

}
