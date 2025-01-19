package com.opmg.ApiGestionStock.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
        @NotNull(message = "300")
        @NotEmpty(message = "300")
        String username,
        @NotNull(message = "301")
        @NotEmpty(message = "301")
        String password
) {
}
