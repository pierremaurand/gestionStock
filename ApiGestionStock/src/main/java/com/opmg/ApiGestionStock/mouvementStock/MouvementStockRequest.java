package com.opmg.ApiGestionStock.mouvementStock;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MouvementStockRequest(
        @NotNull(message = "601")
        @PastOrPresent(message = "602")
        LocalDate dateMouvement,
        @NotNull(message = "602")
        TypeMouvement typeMouvement,
        @NotNull(message = "602")
        @Min(value = 1, message = "601")
        Double quantite,
        @NotNull(message = "602")
        @Min(value = 1, message = "601")
        Long article
) {
}
