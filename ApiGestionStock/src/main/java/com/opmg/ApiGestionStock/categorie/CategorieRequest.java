package com.opmg.ApiGestionStock.categorie;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorieRequest(
        Long id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String code,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String designation
) {
}
