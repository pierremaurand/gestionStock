package com.opmg.ApiGestionStock.commandeFournisseur;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeFournisseurRequest(
        @NotNull(message = "1600")
        @Min(value = 1, message = "1601")
        Long commandeFournisseur,
        @NotNull(message = "1602")
        @Min(value = 1, message = "1603")
        Long fournisseur
) {
}
