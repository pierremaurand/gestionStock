package com.opmg.ApiGestionStock.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotNull(message = "Username is mandatory")
        @NotEmpty(message = "Username is mandatory")
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum")
        String password
) {
}
