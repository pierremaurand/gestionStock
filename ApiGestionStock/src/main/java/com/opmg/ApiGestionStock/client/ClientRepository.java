package com.opmg.ApiGestionStock.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByNumeroCNIOrNumeroTelOrEmail(String numeroCNI, String numeroTel, String email);

    Optional<Client> findByNumeroCNI(String numeroCNI);

    Optional<Client> findByNumeroTel(String numeroTel);

    Optional<Client> findByEmail(String email);
}
