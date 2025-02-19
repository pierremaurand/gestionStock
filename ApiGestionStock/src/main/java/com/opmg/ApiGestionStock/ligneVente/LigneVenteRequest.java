package com.opmg.ApiGestionStock.ligneVente;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LigneVenteRequest(
        Long id,
        @NotNull(message = "Aucun article n'a été selectionné")
        @Min(value = 1, message = "L'article selectionné est invalid")
        Long article,
        @NotNull(message = "Aucune quantité n'a été renseigné")
        @Min(value = 1, message = "La quantité doit être supérieur ou &gal à 1")
        Double quantite,
        @NotNull(message = "Aucun prix unitaire n'a été renseigné")
        @Min(value = 1, message = "Le prix unitaire doit être supérieur ou &gal à 1")
        Double prixUnitaire
) {
}
