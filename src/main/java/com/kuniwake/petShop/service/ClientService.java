package com.kuniwake.petShop.service;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.domain.entities.Client;
import com.kuniwake.petShop.domain.repository.AddressRepository;
import com.kuniwake.petShop.domain.repository.ClientRepository;
import com.kuniwake.petShop.dto.ClientDto;
import com.kuniwake.petShop.form.ClientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<ClientDto>> findAllClient() {
        List<Client> clientList = clientRepository.findAll();
        return ResponseEntity.ok(ClientDto.convertToClientDto(clientList));
    }

    public ResponseEntity<ClientDto> findByIdClient(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        return clientOpt.map(c -> ResponseEntity.ok(new ClientDto(c)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<ClientDto> saveClient(ClientForm clientForm, UriComponentsBuilder uriBuilder) {
        Optional<Address> addressOpt = addressRepository.findById(clientForm.getIdAddress());
        if (addressOpt.isPresent()) {
            Client client = clientForm.convertToClient(addressOpt.get());
            clientRepository.save(client);

            URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).body(new ClientDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<ClientDto> updateClient(Long id, ClientForm clientForm) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            clientOpt.get().setFirstName(clientForm.getFirstName());
            clientOpt.get().setLastName(clientForm.getLastName());
            clientOpt.get().setEmail(clientForm.getEmail());
            clientOpt.get().setPassword(clientForm.getPassword());
            clientOpt.get().setCpf(clientForm.getCpf());
            clientOpt.get().setBirthDate(clientForm.getBirthDate());
            clientOpt.get().setActive(true);

            Optional<Address> addressOtp = addressRepository.findById(clientForm.getIdAddress());
            if (addressOtp.isPresent()) {
                clientOpt.get().setAddress(addressOtp.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
            Client client = clientRepository.save(clientOpt.get());
            return ResponseEntity.ok(clientForm.convertToClientDto(client));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Object> deleteClient(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            clientRepository.delete(clientOpt.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
