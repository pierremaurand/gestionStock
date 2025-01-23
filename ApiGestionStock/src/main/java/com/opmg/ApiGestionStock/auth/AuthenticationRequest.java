package com.opmg.ApiGestionStock.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        @NotBlank(message = "200")
        String login,
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        @NotBlank(message = "201")
        String motDePasse
) {
}
