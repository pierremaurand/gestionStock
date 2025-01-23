package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeArticleCommandeRequest(
        @NotNull(message = "1500")
        @Min(value = 1, message = "1501")
        Long commandeId,
        @NotNull(message = "1502")
        @Min(value = 1, message = "1503")
        Long ligneCommandeId,
        @NotNull(message = "1504")
        @Min(value = 1, message = "1505")
        Long articleId
) {
}
