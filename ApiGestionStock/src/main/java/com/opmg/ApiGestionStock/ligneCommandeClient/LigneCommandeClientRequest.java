package com.opmg.ApiGestionStock.ligneCommandeClient;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LigneCommandeClientRequest(
        Long id,
        @NotNull(message = "Aucun article n'a été selectionné")
        @Min(value = 1, message = "L'article selectionné est invalid")
        Long article,
        @NotNull(message = "Aucune quantité n'a été renseigné")
        @Min(value = 1, message = "La quantité commandée doit être supérieur ou égal à 1")
        Double quantite,
        @NotNull(message = "Le prix unitaire est obligatoire")
        @Min(value = 1, message = "Le prix unitaire doit être supérieur ou égal à 1")
        Double prixUnitaire
) {
}
