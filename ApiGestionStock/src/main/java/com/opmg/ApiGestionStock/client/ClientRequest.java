package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.AdresseRequest;
import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        Long id,
        @NotNull(message = "Le nom du client est obligatoire")
        @NotEmpty(message = "Le nom du client est obligatoire")
        @NotBlank(message = "Le nom du client est obligatoire")
        String nom,
        @NotNull(message = "La numéro de CNI du client est obligatoire")
        @NotEmpty(message = "La numéro de CNI du client est obligatoire")
        @NotBlank(message = "La numéro de CNI du client est obligatoire")
        String numeroCNI,
        @NotNull(message = "Le sexe du client est obligatoire")
        Sexe sexe,
        @NotNull(message = "Le numéro de téléphone du client est obligatoire")
        @NotEmpty(message = "Le numéro de téléphone du client est obligatoire")
        @NotBlank(message = "Le numéro de téléphone du client est obligatoire")
        String numeroTel,
        @Email(message = "L'adresse email du client est invalid")
        String email,
        @Valid
        @NotNull(message = "L'adresse du client est obligatoire")
        AdresseRequest adresse
) {
}
