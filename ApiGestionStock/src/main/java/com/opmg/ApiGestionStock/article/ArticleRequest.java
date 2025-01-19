package com.opmg.ApiGestionStock.article;

import jakarta.validation.constraints.*;

public record ArticleRequest(
        Long id,
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        String code,
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        String designation,
        @Positive(message = "202")
        Double prixUnitaireHt,
        @Positive(message = "203")
        @Min(value = 0, message = "204")
        @Max(value = 100, message = "205")
        Double tauxTva,
        @NotNull(message = "206")
        Long categorieId
) {
}
