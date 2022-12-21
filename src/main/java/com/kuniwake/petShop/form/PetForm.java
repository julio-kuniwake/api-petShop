package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Client;
import com.kuniwake.petShop.domain.entities.Pet;
import com.kuniwake.petShop.dto.PetDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetForm { // 'PetForm' usado quando é uma requisição que o Usuario envia para API

    private Long id;
    private String name;
    private String breed;
    private LocalDate age;
    private Long clientId;

    public Pet convertToPet(Client client) {
        return new Pet(name, breed, age, client);
    }

    public PetDto convertToPetDto(Pet pet) {
        return new PetDto(pet);
    }
}
