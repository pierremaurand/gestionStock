package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FournisseurRequest(
        @NotNull(message = "700")
        @NotEmpty(message = "700")
        @NotBlank(message = "700")
        String nom,
        String numeroCNI,
        Sexe sexe,
        @NotNull(message = "701")
        @NotEmpty(message = "701")
        @NotBlank(message = "701")
        String numeroTel,
        @Email(message = "702")
        String email
) {
}
