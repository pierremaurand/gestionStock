package com.opmg.ApiGestionStock.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotNull(message = "Le login est obligatoire")
        @NotEmpty(message = "Le login est obligatoire")
        @NotBlank(message = "Le login est obligatoire")
        String login,
        @NotNull(message = "Le mot de passe est obligatoire")
        @NotEmpty(message = "Le mot de passe est obligatoire")
        @NotBlank(message = "Le mot de passe est obligatoire")
        String motDePasse
) {
}
