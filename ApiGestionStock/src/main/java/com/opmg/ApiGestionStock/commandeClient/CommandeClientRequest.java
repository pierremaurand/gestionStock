package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.common.EtatCommande;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClientRequest;
import com.opmg.ApiGestionStock.mouvementStock.TypeMouvement;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CommandeClientRequest(
        Long id,
        @NotNull(message = "Le code de la commande est obligatoire")
        @NotEmpty(message = "Le code de la commande est obligatoire")
        @NotBlank(message = "Le code de la commande est obligatoire")
        String code,
        @NotNull(message = "La date de la commande est obligatoire")
        @PastOrPresent(message = "La date doit être passée ou actuelle")
        LocalDate dateCommande,
        @NotNull(message = "L'état de la commande doit être renseigné")
        EtatCommande etatCommande,
        @NotNull(message = "Le client doit être selectionné")
        @Min(value = 1, message = "Le client selectionné n'est pas valid")
        Long client,
        @NotNull(message = "Aucun article n'a été commandé")
        @NotEmpty(message = "Aucun article n'a été commandé")
        List<LigneCommandeClientRequest> ligneCommandeClients
) {
}
