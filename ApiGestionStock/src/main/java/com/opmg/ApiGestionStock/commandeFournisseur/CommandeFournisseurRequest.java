package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.common.EtatCommande;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CommandeFournisseurRequest(
        @NotNull(message = "600")
        @NotEmpty(message = "600")
        @NotBlank(message = "600")
        String code,
        @NotNull(message = "601")
        @PastOrPresent(message = "602")
        LocalDate dateCommande,
        @NotNull(message = "603")
        @Min(value = 1, message = "604")
        Long fournisseur,
        @NotNull(message = "605")
        EtatCommande etatCommande,
        @NotNull(message = "606")
        @NotEmpty(message = "606")
        List<LigneCommandeFournisseurRequest> ligneCommandeFournisseurs
) {
}
