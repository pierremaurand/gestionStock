package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ChangeDateCommandeRequest(
        @NotNull(message = "1500")
        @Min(value = 1, message = "1501")
        Long commande,
        @NotNull(message = "601")
        @PastOrPresent(message = "602")
        LocalDate dateCommande
) {
}
