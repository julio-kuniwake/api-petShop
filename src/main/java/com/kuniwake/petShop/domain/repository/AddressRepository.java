package com.kuniwake.petShop.domain.repository;

import com.kuniwake.petShop.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
