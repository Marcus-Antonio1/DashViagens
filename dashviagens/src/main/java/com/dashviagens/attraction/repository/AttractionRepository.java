package com.dashviagens.attraction.repository;

import java.util.List;
import java.util.Optional;

import com.dashviagens.attraction.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    /**
     * JOIN FETCH carrega o country junto com a atração em um query
     * evitando LazyInitializationException ao acessar attraction.country.code
     * fora da sessao JPA (open-in-view=false).
     */
    @Query("SELECT a FROM Attraction a JOIN FETCH a.country WHERE a.country.code = :code")
    List<Attraction> findByCountry_Code(@Param("code") String code);

    /**
     * Mesmo motivo: carregar o country junto ao buscar por id.
     */
    @Query("SELECT a FROM Attraction a JOIN FETCH a.country WHERE a.id = :id")
    Optional<Attraction> findByIdWithCountry(@Param("id") Long id);
}