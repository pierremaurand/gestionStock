package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangeEtatCommandeRequest(
        @NotNull(message = "1100")
        @Min(value = 1, message = "1101")
        Long commandeId,
        @NotNull(message = "1102")
        EtatCommande etatCommande
) {
}
