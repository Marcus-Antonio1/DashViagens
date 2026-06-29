package com.dashviagens.cost.repository;

import java.util.Optional;

import com.dashviagens.cost.model.CountryCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryCostRepository extends JpaRepository<CountryCost, Long> {

    Optional<CountryCost> findByCountryCode(String countryCode);
}
