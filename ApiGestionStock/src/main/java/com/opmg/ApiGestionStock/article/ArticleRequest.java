package com.opmg.ApiGestionStock.article;

import jakarta.validation.constraints.*;

public record ArticleRequest(
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        @NotBlank(message = "100")
        String code,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        @NotBlank(message = "101")
        String designation,
        @NotNull(message = "102")
        @Positive(message = "103")
        Double prixUnitaireHt,
        @NotNull(message = "104")
        @Min(value = 0, message = "105")
        @Max(value = 100, message = "106")
        Double tauxTva,
        @NotNull(message = "107")
        @Min(value = 1, message = "108")
        Long categorie
) {
}
