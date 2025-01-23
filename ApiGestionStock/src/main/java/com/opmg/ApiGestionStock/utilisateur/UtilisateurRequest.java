package com.opmg.ApiGestionStock.utilisateur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UtilisateurRequest(
        @NotNull(message = "1700")
        @NotEmpty(message = "1700")
        @NotBlank(message = "1700")
        String nom,
        @NotNull(message = "1701")
        @NotEmpty(message = "1701")
        @NotBlank(message = "1701")
        String login
) {
}
