package com.kuniwake.petShop.dto;

import com.kuniwake.petShop.domain.entities.Pet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PetDto {
    private Long id;
    private String name;
    private String breed;
    private LocalDate age;

    public PetDto(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.breed = pet.getBreed();
        this.age = pet.getAge();
    }

    public static List<PetDto> convertToPetDto(List<Pet> pets) {
        return pets.stream().map(PetDto::new).collect(Collectors.toList());
    }
}
