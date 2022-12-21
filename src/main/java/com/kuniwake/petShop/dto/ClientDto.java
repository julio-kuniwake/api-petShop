package com.kuniwake.petShop.dto;

import com.kuniwake.petShop.domain.entities.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClientDto { // 'ClienteDto' usado quando uma requisição está saindo da API para o Cliente
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private boolean active;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
        this.active = client.isActive();
    }

    public static List<ClientDto> convertToClientDto(List<Client> clientList) {
        return clientList.stream().map(ClientDto::new).collect(Collectors.toList());
    }
}
