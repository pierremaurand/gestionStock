package com.opmg.ApiGestionStock.ligneCommandeClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.article.ArticleResponse;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
import com.opmg.ApiGestionStock.commandeClient.CommandeClientResponse;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LigneCommandeClientResponse {
    private Long id;
    private ArticleResponse article;
    private Double quantite;
    private Double prixUnitaire;
    private CommandeClientResponse commandeClient;
}
