package com.dashviagens.country.repository;

import java.util.Optional;

import com.dashviagens.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCode(String code);
}
