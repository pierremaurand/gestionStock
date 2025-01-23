package com.opmg.ApiGestionStock.role;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleNameRequest(
        @NotNull(message = "500")
        @Min(value = 1, message = "500")
        Long id,
        @NotNull(message = "500")
        @NotEmpty(message = "500")
        String name
) {
}
