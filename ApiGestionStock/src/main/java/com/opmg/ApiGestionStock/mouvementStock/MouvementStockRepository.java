package com.opmg.ApiGestionStock.mouvementStock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
    Page<MouvementStock> findAllByIdIn(List<Long> mouvementStocksIds, Pageable pageable);
}
