package com.opmg.ApiGestionStock.commandeClient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {
    Optional<CommandeClient> findByCode(String code);
    boolean existsByCode(String code);
}
