package com.opmg.ApiGestionStock.commandeFournisseur;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CommandeFournisseurRequest(
        Long id,
        @NotNull(message = "700")
        @NotEmpty(message = "700")
        String code,
        @NotNull(message = "701")
        @NotEmpty(message = "701")
        String dateCommande,
        @NotNull(message = "702")
        Long fournisseur
) {
}
