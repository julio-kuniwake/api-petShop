package com.kuniwake.petShop.domain.resource;

import com.kuniwake.petShop.dto.PetDto;
import com.kuniwake.petShop.form.PetForm;
import com.kuniwake.petShop.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/pet")
public class PetResource {

    @Autowired
    PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPet(String name) {
        try {
            return this.petService.findAllPet(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel Buscar Pets!");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDto> getByIdPet(@PathVariable Long id) {
        try {
            return this.petService.findByIdPet(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel buscar o Pet!");
        }
    }

    @PostMapping
    public ResponseEntity<PetDto> sendSevePet(@RequestBody @Valid PetForm petForm, UriComponentsBuilder uriBuilder) {
        try {
            return this.petService.savePet(petForm, uriBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel salvar!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetDto> sendUpdatePet(@PathVariable Long id, @RequestBody @Valid PetForm petForm) {
        try {
            return this.petService.updatePet(id, petForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel atualizar Pet!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> sendRemovePet(@PathVariable Long id) {
        try {
            return this.petService.deletePet(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel deletar Pet!");
        }
    }
}
