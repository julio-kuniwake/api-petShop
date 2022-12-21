package com.kuniwake.petShop.service;

import com.kuniwake.petShop.domain.entities.Client;
import com.kuniwake.petShop.domain.entities.Pet;
import com.kuniwake.petShop.domain.repository.ClientRepository;
import com.kuniwake.petShop.domain.repository.PetRepository;
import com.kuniwake.petShop.dto.PetDto;
import com.kuniwake.petShop.form.PetForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    ClientRepository clientRepository;


    public ResponseEntity<List<PetDto>> findAllPet(String name) {
        List<Pet> pets;
        if (name == null || name.isEmpty()) {
            pets = petRepository.findAll();
        } else {
            pets = petRepository.findByName(name);
        }
        return new ResponseEntity<>(PetDto.convertToPetDto(pets), HttpStatus.OK);
    }

    public ResponseEntity<PetDto> findByIdPet(Long id) {
        Optional<Pet> petOpt = petRepository.findById(id);
        return petOpt.map(p -> ResponseEntity.ok(new PetDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<PetDto> savePet(PetForm petForm, UriComponentsBuilder uriBuilder) {
        Optional<Client> clientOpt = clientRepository.findById(petForm.getClientId());
        if (clientOpt.isPresent()) {
            Pet pet = petForm.convertToPet(clientOpt.get());
            petRepository.save(pet);
            URI uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
            return ResponseEntity.created(uri).body(new PetDto(pet));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<PetDto> updatePet(Long id, PetForm petForm) {
        Optional<Pet> petOpt = petRepository.findById(id);
        if (petOpt.isPresent()) {
            petOpt.get().setName(petForm.getName());
            petOpt.get().setBreed(petForm.getBreed());
            petOpt.get().setAge(petForm.getAge());
            Pet pet = petRepository.save(petOpt.get());

            return ResponseEntity.ok(petForm.convertToPetDto(pet));
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
