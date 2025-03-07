package com.opmg.ApiGestionStock.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.categorie.CategorieResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleResponse {
    private Long id;
    private String code;
    private String designation;
    private Double prixUnitaireHt;
    private Double tauxTva;
    private Double prixUnitaireTtc;
    private CategorieResponse categorie;
    private String value;
    private byte[] photo;
}
