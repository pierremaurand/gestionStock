package com.opmg.ApiGestionStock.vente;

import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteRequest;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public record VenteRequest(
        @NotNull
        @NotEmpty
        @NotBlank
        String code,
        @NotNull
        LocalDate dateVente,
        @NotNull
        @NotEmpty
        List<LigneVenteRequest> ligneVentes
) {
}
