package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.AdresseRequest;
import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UtilisateurRequest(
        Long id,
        @NotNull(message = "Le nom est obligatoire")
        @NotEmpty(message = "Le nom est obligatoire")
        @NotBlank(message = "Le nom est obligatoire")
        String nom,
        @NotNull(message = "Le sexe est obligatoire")
        Sexe sexe,
        @NotNull(message = "Le login est obligatoire")
        @NotEmpty(message = "Le login est obligatoire")
        @NotBlank(message = "Le login est obligatoire")
        String login,
        @NotNull(message = "Le numéro de téléphone est obligatoire")
        @NotEmpty(message = "Le numéro de téléphone est obligatoire")
        @NotBlank(message = "Le numéro de téléphone est obligatoire")
        String numeroTel,
        @Email(message = "L'adresse email est invalid")
        String email,
        @Valid
        @NotNull(message = "L'adresse de l'utilisateur est obligatoire")
        AdresseRequest adresse
) {
}
