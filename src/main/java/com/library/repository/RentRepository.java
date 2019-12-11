package com.library.repository;

import com.library.domain.rents.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent, Long> {

    Optional<Rent> getRentById(Long Id);
}
