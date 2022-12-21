package com.kuniwake.petShop.domain.repository;

import com.kuniwake.petShop.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
