package com.opmg.ApiGestionStock.ligneCommandeClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long>{
    Page<LigneCommandeClient> findAllByIdIn(List<Long> ligneCommandeClientsIds, Pageable pageable);
}
