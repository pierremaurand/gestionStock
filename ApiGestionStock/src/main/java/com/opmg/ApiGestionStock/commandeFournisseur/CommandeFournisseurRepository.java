package com.opmg.ApiGestionStock.commandeFournisseur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {
    Optional<CommandeFournisseur> findByCode(String code);
    boolean existsByCode(String code);
}
