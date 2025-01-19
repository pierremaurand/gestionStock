package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseur;
import com.opmg.ApiGestionStock.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LigneCommandeFournisseur extends BaseEntity {
    @ManyToOne
    private Article article;
    private double quantite;
    private double prixUnitaire;
    @ManyToOne
    private CommandeFournisseur commandeFournisseur;
}
