package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseur;
import com.opmg.ApiGestionStock.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fournisseur extends BaseEntity {
    private String nom;
    private String prenom;
    private String photo;
    private String numeroTel;

    @OneToMany(mappedBy = "fournisseur")
    private Collection<CommandeFournisseur> commandeFournisseurs;
}
