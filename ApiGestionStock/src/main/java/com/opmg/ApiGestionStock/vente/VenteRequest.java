package com.opmg.ApiGestionStock.vente;

import com.opmg.ApiGestionStock.ligneVente.LigneVenteRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record VenteRequest(
        Long id,
        @NotNull(message = "Le code de la vente est obligatoire")
        @NotEmpty(message = "Le code de la vente est obligatoire")
        @NotBlank(message = "Le code de la vente est obligatoire")
        String code,
        @NotNull(message = "La date du la vente est obligatoire")
        @PastOrPresent(message = "La date de la vente doit être une date passée ou actuelle")
        LocalDate dateVente,
        @NotNull(message = "Aucun article n'a été selectionné")
        @NotEmpty(message = "Aucun article n'a été selectionné")
        List<LigneVenteRequest> ligneVentes
) {
}
