package com.opmg.ApiGestionStock.utilisateur;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull
        @Min(value = 1)
        Long utilisateur,
        @NotNull
        @NotEmpty
        @NotBlank
        String motDePasse,
        @NotNull
        @NotEmpty
        @NotBlank
        String confirmMotDePasse
) {
}
