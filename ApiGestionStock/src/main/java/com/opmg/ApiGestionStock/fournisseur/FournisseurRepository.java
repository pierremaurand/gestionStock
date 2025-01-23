package com.opmg.ApiGestionStock.fournisseur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    Optional<Fournisseur> findByNumeroCNI(String numeroCNI);
    Optional<Fournisseur> findByNumeroTel(String numeroTel);
    Optional<Fournisseur> findByEmail(String email);
    boolean existsByNumeroCNI(String numeroCNI);
}
