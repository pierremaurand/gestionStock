package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.common.EtatCommande;
import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommandeFournisseur extends BaseEntity {
    @Column(unique = true)
    private String code;
    private LocalDate dateCommande;
    private EtatCommande etatCommande;

    @ManyToOne
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur")
    private Collection<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @Transient
    public Long getFournisseurId() {
        return fournisseur.getId();
    }

    @Transient
    public List<Long> getLigneCommandeIds(){
        return ligneCommandeFournisseurs.stream()
                .map(LigneCommandeFournisseur::getId)
                .toList();
    }

    @Transient
    public boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(etatCommande);
    }
}
