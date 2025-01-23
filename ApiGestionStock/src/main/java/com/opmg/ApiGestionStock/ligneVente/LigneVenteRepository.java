package com.opmg.ApiGestionStock.ligneVente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
    Page<LigneVente> findAllByIdIn(List<Long> ligneVentesIds, Pageable pageable);
}
