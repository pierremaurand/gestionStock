package com.opmg.ApiGestionStock.mouvementStock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.article.Article;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MouvementStockResponse {
    private Long id;
    private LocalDate dateMouvement;
    private Double quantite;
    private TypeMouvement typeMouvement;
    private Long article;
}
