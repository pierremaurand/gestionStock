package com.opmg.ApiGestionStock.ligneCommandeClient;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
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
public class LigneCommandeClient extends BaseEntity {
    @ManyToOne
    private Article article;
    private Double quantite;
    private Double prixUnitaire;
    @ManyToOne
    private CommandeClient commandeClient;

    @Transient
    public Long getArticleId(){
        return article.getId();
    }

    @Transient
    public Long getCommandeClientId() {
        return commandeClient.getId();
    }
}
