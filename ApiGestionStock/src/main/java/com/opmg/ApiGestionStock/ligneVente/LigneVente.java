package com.opmg.ApiGestionStock.ligneVente;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.vente.Vente;
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
public class LigneVente extends BaseEntity {
    @ManyToOne
    private Article article;
    private Double quantite;
    private Double prixUnitaire;
    @ManyToOne
    private Vente vente;

    @Transient
    public Long getArticleId(){
        return article.getId();
    }

    @Transient
    public Long getVenteId(){
        return vente.getId();
    }
}
