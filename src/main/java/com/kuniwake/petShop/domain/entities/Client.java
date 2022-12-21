package com.kuniwake.petShop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private boolean active = true;
    @ManyToOne // Muitos 'Client' para um 'Address'
    private Address address;
    @OneToMany(mappedBy = "client") // Um 'Dono' para muitos 'Pet'
    private List<Pet> petList = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password, String cpf, LocalDate birthDate, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

}
