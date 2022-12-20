package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Pet;
import com.kuniwake.petShop.dto.PetDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetForm {

    private Long id;
    private String name;
    private String breed;
    private LocalDate age;

    public Pet convertToPet() {
        return new Pet(name, breed, age);
    }

    public PetDto convertToPetDto(Pet pet) {
        return new PetDto(pet);
    }
}
