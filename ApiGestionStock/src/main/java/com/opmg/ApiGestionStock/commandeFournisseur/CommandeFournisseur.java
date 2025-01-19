package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collection;

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

    @ManyToOne
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur")
    private Collection<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    public Long getFournisseurId() {
        return fournisseur.getId();
    }
}
