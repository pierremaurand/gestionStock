package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.AdresseRequest;
import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FournisseurRequest(
        Long id,
        @NotNull(message = "Le nom du fournisseur est obligatoire")
        @NotEmpty(message = "Le nom du fournisseur est obligatoire")
        @NotBlank(message = "Le nom du fournisseur est obligatoire")
        String nom,
        String numeroCNI,
        Sexe sexe,
        @NotNull(message = "Le numéro de téléphone du fournisseur est obligatoire")
        @NotEmpty(message = "Le numéro de téléphone du fournisseur est obligatoire")
        @NotBlank(message = "Le numéro de téléphone du fournisseur est obligatoire")
        String numeroTel,
        @Email(message = "L'adresse email du fournisseur est invalid")
        String email,
        @Valid
        @NotNull(message = "L'adresse du fournisseur est obligatoire")
        AdresseRequest adresse
) {
}
