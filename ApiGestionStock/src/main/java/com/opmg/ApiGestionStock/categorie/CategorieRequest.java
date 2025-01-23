package com.opmg.ApiGestionStock.categorie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorieRequest(
        @NotNull(message = "300")
        @NotEmpty(message = "300")
        @NotBlank(message = "300")
        String code,
        @NotNull(message = "301")
        @NotEmpty(message = "301")
        @NotBlank(message = "301")
        String designation
) {
}
