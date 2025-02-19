package com.opmg.ApiGestionStock.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AdresseRequest(
        @NotNull(message = "L'adresse 1 est obligatoire")
        @NotBlank(message = "L'adresse 1 est obligatoire")
        @NotEmpty(message = "L'adresse 1 est obligatoire")
        String adresse1,
        String adresse2,
        @NotNull(message = "La ville est obligatoire")
        @NotBlank(message = "La ville est obligatoire")
        @NotEmpty(message = "La ville est obligatoire")
        String ville,
        String codePostale,
        @NotNull(message = "Le pays est obligatoire")
        @NotBlank(message = "Le pays est obligatoire")
        @NotEmpty(message = "Le pays est obligatoire")
        String pays
) {
}
