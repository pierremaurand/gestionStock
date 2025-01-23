package com.opmg.ApiGestionStock.ligneVente;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LigneVenteRequest(
        @NotNull(message = "1000")
        @Min(value = 1, message = "1001")
        Long article,
        @NotNull(message = "1002")
        @Min(value = 1, message = "1003")
        Double quantite,
        @NotNull(message = "1004")
        @Min(value = 1, message = "1005")
        Double prixUnitaire,
        @NotNull(message = "1006")
        @Min(value = 1, message = "1007")
        Long vente
) {
}
