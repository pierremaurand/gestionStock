package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CommandeFournisseurRequest(
        Long id,
        @NotNull(message = "Le code de la commande est obligatoire")
        @NotEmpty(message = "Le code de la commande est obligatoire")
        @NotBlank(message = "Le code de la commande est obligatoire")
        String code,
        @NotNull(message = "La date de la commande est obligatoire")
        @PastOrPresent(message = "La date de la commande doit être passée ou actuelle")
        LocalDate dateCommande,
        @NotNull(message = "Aucun fournisseur n'a été selectionné")
        @Min(value = 1, message = "Le fournisseur selectionné est invalid")
        Long fournisseur,
        @NotNull(message = "Aucun article n'a été commandé")
        @NotEmpty(message = "Aucun article n'a été commandé")
        List<LigneCommandeFournisseurRequest> ligneCommandeFournisseurs
) {
}
