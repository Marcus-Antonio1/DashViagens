package com.dashviagens.cost.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryCostRepository extends JpaRepository<CountryCost, Long> {

    // findByCountry_Code: navega countryCost.country.code
    Optional<CountryCost> findByCountry_Code(String countryCode);
}
