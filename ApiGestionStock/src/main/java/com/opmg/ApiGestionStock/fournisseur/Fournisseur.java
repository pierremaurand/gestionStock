package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseur;
import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.common.Sexe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
public class Fournisseur extends BaseEntity {
    private String nom;
    @Column(unique = true)
    private String numeroCNI;
    private Sexe sexe;
    @Column(unique = true)
    private String numeroTel;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "fournisseur")
    private Collection<CommandeFournisseur> commandeFournisseurs;

    @Transient
    public List<Long> getCommandeFournisseursId(){
        return commandeFournisseurs.stream().map(CommandeFournisseur::getId).toList();
    }
}
