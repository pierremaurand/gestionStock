package com.opmg.ApiGestionStock.utilisateur;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.common.AdresseResponse;
import com.opmg.ApiGestionStock.common.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilisateurResponse {
    private Long id;
    private String nom;
    private String login;
    private Sexe sexe;
    private String numeroTel;
    private String email;
    private byte[] photo;
    private AdresseResponse adresse;
}
