package com.opmg.ApiGestionStock.vente;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Vente extends BaseEntity {
    @Column(unique = true)
    private String code;
    private LocalDate dateVente;

    @OneToMany(mappedBy = "vente")
    private Collection<LigneVente> ligneVentes;
}
