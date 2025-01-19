package com.opmg.ApiGestionStock.client;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroCNI;
    private Sexe sexe;
    private String numeroTel;
    private String email;
}
