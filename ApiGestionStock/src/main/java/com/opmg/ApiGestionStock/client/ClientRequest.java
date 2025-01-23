package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        @NotNull(message = "400")
        @NotEmpty(message = "400")
        @NotBlank(message = "400")
        String nom,
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        @NotBlank(message = "401")
        String numeroCNI,
        @NotNull(message = "402")
        Sexe sexe,
        @NotNull(message = "403")
        @NotEmpty(message = "403")
        @NotBlank(message = "403")
        String numeroTel,
        @Email(message = "404")
        String email
) {
}
