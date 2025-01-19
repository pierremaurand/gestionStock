package com.opmg.ApiGestionStock.mouvementStock;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MouvementStock extends BaseEntity {
    private LocalDate dateMouvement;
    private double quantite;
    private TypeMouvement typeMouvement;
    @ManyToOne
    private Article article;
}
