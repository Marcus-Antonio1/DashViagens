package com.dashviagens.attraction.repository;

import java.util.List;

import com.dashviagens.attraction.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    List<Attraction> findByCountryCode(String countryCode);
}
