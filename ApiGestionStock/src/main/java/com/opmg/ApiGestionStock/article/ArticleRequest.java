package com.opmg.ApiGestionStock.article;

import jakarta.validation.constraints.*;

public record ArticleRequest(
        Long id,
        @NotNull(message = "Le code de l'article est obligatoire")
        @NotEmpty(message = "Le code de l'article est obligatoire")
        @NotBlank(message = "Le code de l'article est obligatoire")
        String code,
        @NotNull(message = "La désignation de l'article est obligatoire")
        @NotEmpty(message = "La désignation de l'article est obligatoire")
        @NotBlank(message = "La désignation de l'article est obligatoire")
        String designation,
        @NotNull(message = "Le prix unitaire HT de l'article est obligatoire")
        @Positive(message = "Le prix unitaire HT doit être un nombre positif")
        Double prixUnitaireHt,
        @NotNull(message = "Le taux TVA de l'article est obligatoire")
        @Positive(message = "Le taux TVA doit être un nombre positif")
        @Max(value = 100, message = "Le taux TVA doit être inférieur ou égal à 100%")
        Double tauxTva,
        @NotNull(message = "Aucune catégorie n'a été selectionnée")
        @Min(value = 1, message = "La catégorie selectionnée n'est pas valid")
        Long categorie
) {
}
