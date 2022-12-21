package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.domain.entities.Client;
import com.kuniwake.petShop.dto.ClientDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class ClientForm { // 'ClientForm' usado quando é uma requisição que o Usuario envia para API
    private Long id;
    @NotNull @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    @CPF
    private String cpf;
    private LocalDate birthDate;
    private boolean active = true;
    @NotNull
    private Long idAddress;

    public Client convertToClient(Address address) {
        return new Client(firstName, lastName, email, password, cpf, birthDate, address);
    }

    public ClientDto convertToClientDto(Client client) {
        return new ClientDto(client);
    }
}
