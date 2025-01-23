package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.common.EtatCommande;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClientRequest;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record CommandeClientRequest(
        @NotNull(message = "500")
        @NotEmpty(message = "500")
        @NotBlank(message = "500")
        String code,
        @NotNull(message = "501")
        @PastOrPresent(message = "502")
        LocalDate dateCommande,
        @NotNull(message = "503")
        @Min(value = 1, message = "504")
        Long client,
        @NotNull(message = "505")
        EtatCommande etatCommande,
        @NotNull(message = "506")
        @NotEmpty(message = "506")
        List<LigneCommandeClientRequest> ligneCommandeClients
) {
}
