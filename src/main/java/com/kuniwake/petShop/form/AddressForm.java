package com.kuniwake.petShop.form;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressForm { // 'AddressForm' usado quando é uma requisição que o Usuario envia para API
    private Long id;
    private String cep;
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
