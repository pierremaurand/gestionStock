package com.opmg.ApiGestionStock.categorie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorieRequest(
        Long id,
        @NotNull(message = "Le code de la catégorie est obligatoire")
        @NotEmpty(message = "Le code de la catégorie est obligatoire")
        @NotBlank(message = "Le code de la catégorie est obligatoire")
        String code,
        @NotNull(message = "La désignation de la catégorie est obligatoire")
        @NotEmpty(message = "La désignation de la catégorie est obligatoire")
        @NotBlank(message = "La désignation de la catégorie est obligatoire")
        String designation
) {
}
