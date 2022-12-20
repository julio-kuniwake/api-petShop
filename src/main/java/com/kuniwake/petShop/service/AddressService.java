package com.kuniwake.petShop.service;

import com.kuniwake.petShop.domain.entities.Address;
import com.kuniwake.petShop.domain.repository.AddressRepository;
import com.kuniwake.petShop.dto.AddressDto;
import com.kuniwake.petShop.form.AddressForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<AddressDto>> findAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(AddressDto.convertToAddressDto(addressList));
    }

    public ResponseEntity<AddressDto> findByIdAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(a -> ResponseEntity.ok(new AddressDto(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<AddressDto> saveAddress(AddressForm addressForm, UriComponentsBuilder uriBuilder) {
        Address address = addressForm.convertToAddress();
        addressRepository.save(address);
        URI uri = uriBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(new AddressDto(address));
    }

    public ResponseEntity<AddressDto> updateAddres(Long id, AddressForm addressForm) {
        Optional<Address> addressOpt = addressRepository.findById(id);
        if (addressOpt.isPresent()) {
            addressOpt.get().setCep(addressForm.getCep());
            addressOpt.get().setState(addressForm.getState());
            addressOpt.get().setCity(addressForm.getCity());
            addressOpt.get().setDistrict(addressForm.getDistrict());
            addressOpt.get().setStreet(addressForm.getStreet());
            addressOpt.get().setNumber(addressForm.getNumber());
            Address address = addressRepository.save(addressOpt.get());

            return ResponseEntity.ok(addressForm.convertToAddressDto(address));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deleteAddress(Long id) {
        Optional<Address> addressOpt = addressRepository.findById(id);
        if (addressOpt.isPresent()) {
            addressRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
