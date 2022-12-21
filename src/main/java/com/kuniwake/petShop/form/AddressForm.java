package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.dto.AddressDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AddressForm { // 'AddressForm' usado quando é uma requisição que o Usuario envia para API
    private Long id;
    @NotNull @NotEmpty @Length(min = 8, max = 8)
    private String cep;
    @Length(min = 2)
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;

    public Address convertToAddress(){
        return new Address(cep, state, city, district, street, number);
    }

    public AddressDto convertToAddressDto(Address address){
        return new AddressDto(address);
    }
}
