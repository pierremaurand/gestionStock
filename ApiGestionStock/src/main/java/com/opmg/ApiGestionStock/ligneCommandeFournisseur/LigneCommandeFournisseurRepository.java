package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {
    Page<LigneCommandeFournisseur> findAllByIdIn(List<Long> ligneCommandeFournisseursIds, Pageable pageable);
}
