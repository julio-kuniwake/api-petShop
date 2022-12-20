package com.kuniwake.petShop.service;

import com.kuniwake.petShop.domain.entities.Pet;
import com.kuniwake.petShop.domain.repository.PetRepository;
import com.kuniwake.petShop.dto.PetDto;
import com.kuniwake.petShop.form.PetForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<PetDto> findAllPet(String name) {
        List<Pet> pets;
        if (name == null || name.isEmpty()) {
            pets = petRepository.findAll();
        } else {
            pets = petRepository.findByName(name);
        }
        return PetDto.convertToPetDto(pets);
    }

    public ResponseEntity<PetDto> findByIdPet(Long id) {
        Optional<Pet> petOpt = petRepository.findById(id);
        return petOpt.map(p -> ResponseEntity.ok(new PetDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<PetDto> savePet(PetForm petForm, UriComponentsBuilder uriBuilder) {
        Pet pet = petForm.convertToPet();
        petRepository.save(pet);
        URI uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new PetDto(pet));
    }

    public ResponseEntity<PetDto> updatePet(Long id, PetForm petForm) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            pet.get().setName(petForm.getName());
            pet.get().setBreed(petForm.getBreed());
            pet.get().setAge(petForm.getAge());
            return ResponseEntity.ok(petForm.convertToPetDto(petRepository.save(pet.get())));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deletePet(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    public List<Integer> idadePet(List<Pet> pets) {
        List<Integer> idadePets = new ArrayList<>();
        for (Pet pet : pets) {
            idadePets.add(returnIdade(pet.getAge()));
        }
        return idadePets;
    }

    public Integer returnIdade(LocalDate birth) {
        return Period.between(birth, LocalDate.now()).getYears();
    }
}
