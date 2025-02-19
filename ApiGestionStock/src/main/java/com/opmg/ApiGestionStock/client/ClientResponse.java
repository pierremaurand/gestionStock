package com.opmg.ApiGestionStock.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.common.AdresseResponse;
import com.opmg.ApiGestionStock.common.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {
    private Long id;
    private String nom;
    private String numeroCNI;
    private Sexe sexe;
    private String numeroTel;
    private String email;
    private byte[] photo;
    private AdresseResponse adresse;
}
