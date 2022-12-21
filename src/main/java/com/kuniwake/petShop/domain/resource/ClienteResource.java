package com.kuniwake.petShop.domain.resource;

import com.kuniwake.petShop.dto.ClientDto;
import com.kuniwake.petShop.form.ClientForm;
import com.kuniwake.petShop.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClienteResource {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClient() {
        try {
            return this.clientService.findAllClient();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel buscar todos os clientes!");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> getByIdClient(@PathVariable Long id) {
        try {
            return this.clientService.findByIdClient(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel buscar cliente por id!");
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> sendSaveClient(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder) {
        try {
            return this.clientService.saveClient(clientForm, uriBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel cadastrar cliente!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> sendUpdateClient(@PathVariable Long id, @RequestBody @Valid ClientForm clientForm) {
        try {
            return this.clientService.updateClient(id, clientForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Não foi possivel alterar cliente!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> sendDeleteClient(@PathVariable Long id) {
        try {
            return this.clientService.deleteClient(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Não foi possivel deletar cliente");
        }
    }

}
