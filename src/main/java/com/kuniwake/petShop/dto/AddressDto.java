package com.kuniwake.petShop.dto;

import com.kuniwake.petShop.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AddressDto { // 'AddressDto', usado quando uma requisição está saindo da API para o Cliente
    private Long id;
    private String cep;
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;

    public AddressDto(){}

    public AddressDto(Address address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.state = address.getState();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.number = address.getNumber();
    }

    public static List<AddressDto> convertToAddressDto(List<Address> addressList){
        return addressList.stream().map(AddressDto::new).collect(Collectors.toList());
    }
}
