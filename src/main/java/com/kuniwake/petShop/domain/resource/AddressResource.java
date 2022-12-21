package com.kuniwake.petShop.domain.resource;

import com.kuniwake.petShop.dto.AddressDto;
import com.kuniwake.petShop.form.AddressForm;
import com.kuniwake.petShop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddress() {
        try {
            return this.addressService.findAllAddress();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel buscar todos os endereços!");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDto> getByIdAddres(@PathVariable Long id) {
        try {
            return this.addressService.findByIdAddress(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel buscar endereço por id!");
        }
    }

    @PostMapping
    public ResponseEntity<AddressDto> sendSaveAddress(@RequestBody @Valid AddressForm addressForm, UriComponentsBuilder uriBuilder) {
        try {
            return this.addressService.saveAddress(addressForm, uriBuilder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Não foi possivel salvar endereço!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDto> sendUpdateAddress(@PathVariable Long id, @RequestBody @Valid AddressForm addressForm) {
        try {
            return this.addressService.updateAddres(id, addressForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel alterar endereço!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> sendDeleteAddress(@PathVariable Long id) {
        try {
            return this.addressService.deleteAddress(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel deletar endereço!");
        }
    }
}
