package com.opmg.ApiGestionStock.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {
    private UtilisateurResponse utilisateur;
    private String token;
}
