package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeQuantiteCommandeRequest(
        @NotNull(message = "1200")
        @Min(value = 1, message = "1201")
        Long commandeId,
        @NotNull(message = "1202")
        @Min(value = 1, message = "1203")
        Long ligneCommandeId,
        @NotNull(message = "1204")
        @Min(value = 1, message = "1205")
        Double quantite
) {
}
