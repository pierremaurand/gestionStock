package com.opmg.ApiGestionStock.mouvementStock;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MouvementStockRequest(
        Long id,
        @NotNull(message = "La date du mouvement de stock est obligatoire")
        @PastOrPresent(message = "La date du mouvement doit être une date passée ou actuelle")
        LocalDate dateMouvement,
        @NotNull(message = "Le type de mouvement de stock doit être renseigné")
        TypeMouvement typeMouvement,
        Provenance provenance,
        @NotNull(message = "Aucune quantité n'a été renseigné")
        @Min(value = 1, message = "La quantité doit être supérieur ou égal à 1")
        Double quantite,
        @NotNull(message = "Aucun article n'a été selectionné")
        @Min(value = 1, message = "L'article selectionné est invalid")
        Long article
) {
}
