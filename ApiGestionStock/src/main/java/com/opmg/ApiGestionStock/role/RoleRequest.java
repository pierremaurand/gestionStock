package com.opmg.ApiGestionStock.role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoleRequest(
        Long id,
        @NotNull(message = "500")
        @NotEmpty(message = "500")
        String name
) {
}
