package com.opmg.ApiGestionStock.ligneCommandeClient;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LigneCommandeClientRequest(
        @NotNull(message = "800")
        @Min(value = 1, message = "801")
        Long article,
        @NotNull(message = "802")
        @Min(value = 1, message = "803")
        Double quantite,
        @NotNull(message = "804")
        @Min(value = 1, message = "805")
        Double prixUnitaire,
        @NotNull(message = "806")
        @Min(value = 1, message = "807")
        Long commandeClient
) {
}
