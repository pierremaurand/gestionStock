package com.opmg.ApiGestionStock.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoleRequest(
        Long id,
        @NotNull(message = "Le nom du role est obligatoire")
        @NotEmpty(message = "Le nom du role est obligatoire")
        @NotBlank(message = "Le nom du role est obligatoire")
        String name
) {
}
