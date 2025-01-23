package com.opmg.ApiGestionStock.ligneVente;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.article.ArticleResponse;
import com.opmg.ApiGestionStock.vente.VenteResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LigneVenteResponse {
    private Long id;
    private ArticleResponse article;
    private Double quantite;
    private Double prixUnitaire;
    private VenteResponse vente;
}
