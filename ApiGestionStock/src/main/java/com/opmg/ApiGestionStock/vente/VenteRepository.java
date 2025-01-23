package com.opmg.ApiGestionStock.vente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    Optional<Vente> findByCode(String code);
}
