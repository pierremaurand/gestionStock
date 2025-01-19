package com.opmg.ApiGestionStock.categorie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByCode(String code);

    boolean existsByCode(String code);
}
