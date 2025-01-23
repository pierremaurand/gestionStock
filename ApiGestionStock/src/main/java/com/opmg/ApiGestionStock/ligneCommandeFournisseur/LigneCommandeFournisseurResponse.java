package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.article.ArticleResponse;
import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseurResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LigneCommandeFournisseurResponse {
    private Long id;
    private ArticleResponse article;
    private Double quantite;
    private Double prixUnitaire;
    private CommandeFournisseurResponse commandeFournisseur;
}
