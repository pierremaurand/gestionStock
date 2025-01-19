package com.opmg.ApiGestionStock.client;


import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
import jakarta.persistence.Column;
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
public class Client extends BaseEntity {
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String numeroCNI;
    private Sexe sexe;
    @Column(unique = true)
    private String numeroTel;
    private String email;

    @OneToMany(mappedBy = "client")
    private Collection<CommandeClient> commandeClients;

}
