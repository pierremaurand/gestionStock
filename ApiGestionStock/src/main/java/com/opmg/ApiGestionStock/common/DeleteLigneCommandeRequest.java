package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DeleteLigneCommandeRequest(
        @NotNull(message = "1400")
        @Min(value = 1, message = "1401")
        Long commande,
        @NotNull(message = "1402")
        @Min(value = 1, message = "1403")
        Long ligneCommande
) {
}
