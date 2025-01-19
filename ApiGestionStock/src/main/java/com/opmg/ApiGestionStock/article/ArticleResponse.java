package com.opmg.ApiGestionStock.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private Long id;
    private String code;
    private String designation;
    private Double prixUnitaireHt;
    private Double tauxTva;
    private Double prixUnitaireTtc;
    private Long categorie;
    private byte[] photo;
}
