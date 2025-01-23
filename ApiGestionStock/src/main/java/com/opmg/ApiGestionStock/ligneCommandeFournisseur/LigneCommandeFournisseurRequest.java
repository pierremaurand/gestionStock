package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LigneCommandeFournisseurRequest(
        @NotNull(message = "900")
        @Min(value = 1, message = "901")
        Long article,
        @NotNull(message = "902")
        @Min(value = 1, message = "903")
        Double quantite,
        @NotNull(message = "904")
        @Min(value = 1, message = "905")
        Double prixUnitaire,
        @NotNull(message = "906")
        @Min(value = 1, message = "907")
        Long commandeFournisseur
) {
}
