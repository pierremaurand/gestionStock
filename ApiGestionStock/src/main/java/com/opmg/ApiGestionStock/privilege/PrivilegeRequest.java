package com.opmg.ApiGestionStock.privilege;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PrivilegeRequest(
        Long id,
        @NotNull(message = "500")
        @NotEmpty(message = "500")
        String name
) {
}
