package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.domain.entities.Client;
import com.kuniwake.petShop.dto.ClientDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientForm { // 'ClientForm' usado quando é uma requisição que o Usuario envia para API
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private LocalDate birthDate;
    private boolean active = true;
    private Long idAddress;

    public Client convertToClient(Address address) {
        return new Client(firstName, lastName, email, password, cpf, birthDate, address);
    }

    public ClientDto convertToClientDto(Client client) {
        return new ClientDto(client);
    }
}
