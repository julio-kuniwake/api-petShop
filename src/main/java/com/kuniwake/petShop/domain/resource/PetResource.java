package com.kuniwake.petShop.domain.resource;

import com.kuniwake.petShop.dto.PetDto;
import com.kuniwake.petShop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pet")
public class PetResource {

    @Autowired
    PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPet(String name) {
        try {
            return new ResponseEntity<>(this.petService.findAllPet(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel Buscar Pets");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        try {
            return this.petService.findByIdPet(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
}
