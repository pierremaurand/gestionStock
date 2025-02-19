package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClient;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseur;
import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article extends BaseEntity {
    @Column(unique = true)
    private String code;
    private String designation;
    private String photo;
    private Double prixUnitaireHt;
    private Double tauxTva;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "article")
    private Collection<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private Collection<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private Collection<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "article")
    private Collection<MouvementStock> mouvementStocks;

    @Transient
    public Double getPrixUnitaireTtc() {
        return (double) (Math.round((1 + tauxTva / 100) * prixUnitaireHt)*100)/100;
    }

    @Transient
    public Long getCategorieId() {
        return categorie.getId();
    }

    @Transient
    public List<Long> getLigneCommandeClientsIds(){
        return ligneCommandeClients.stream()
                .map(LigneCommandeClient::getId)
                .toList();
    }

    @Transient
    public List<Long> getLigneCommandeFournisseursIds(){
        return ligneCommandeFournisseurs.stream()
                .map(LigneCommandeFournisseur::getId)
                .toList();
    }

    @Transient
    public List<Long> getLigneVentesIds(){
        return ligneVentes.stream()
                .map(LigneVente::getId)
                .toList();
    }

    @Transient
    public List<Long> getMouvementStocksIds() {
        return mouvementStocks.stream()
                .map(MouvementStock::getId)
                .toList();
    }
}
