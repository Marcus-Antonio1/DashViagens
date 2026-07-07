package com.dashviagens.cost.repository;

import java.util.List;
import java.util.Optional;

import com.dashviagens.cost.model.CountryCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryCostRepository extends JpaRepository<CountryCost, Long> {

    @Query("SELECT c FROM CountryCost c JOIN FETCH c.country WHERE c.country.code = :code")
    Optional<CountryCost> findByCountry_Code(@Param("code") String code);

    @Query("SELECT c FROM CountryCost c JOIN FETCH c.country")
    List<CountryCost> findAllWithCountry();
}