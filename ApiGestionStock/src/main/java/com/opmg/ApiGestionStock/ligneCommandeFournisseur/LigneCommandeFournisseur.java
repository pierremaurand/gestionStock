package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseur;
import com.opmg.ApiGestionStock.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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
    private Double quantite;
    private Double prixUnitaire;
    @ManyToOne
    private CommandeFournisseur commandeFournisseur;

    @Transient
    public Long getArticleId(){
        return article.getId();
    }

    @Transient
    public Long getCommandeFournisseurId(){
        return commandeFournisseur.getId();
    }
}
