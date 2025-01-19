package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.client.Client;
import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClient;
import jakarta.persistence.*;
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
public class CommandeClient extends BaseEntity {
    @Column(unique = true)
    private String code;
    private LocalDate dateCommande;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commandeClient")
    private Collection<LigneCommandeClient> ligneCommandeClients;

    @Transient
    public Long getClientId() {
        return client.getId();
    }
}
