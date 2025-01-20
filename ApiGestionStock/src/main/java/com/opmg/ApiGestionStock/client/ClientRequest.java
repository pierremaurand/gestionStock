package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        Long id,
        @NotNull(message = "400")
        @NotEmpty(message = "400")
        String nom,
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        String prenom,
        @NotNull(message = "402")
        @NotEmpty(message = "402")
        String numeroCNI,
        @NotNull(message = "403")
        Sexe sexe,
        @NotNull(message = "404")
        @NotEmpty(message = "404")
        String numeroTel,
        @Email(message = "405")
        String email
) {
}
