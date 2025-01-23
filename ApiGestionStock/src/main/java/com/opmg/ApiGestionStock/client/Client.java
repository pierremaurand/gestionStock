package com.opmg.ApiGestionStock.client;


import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
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
public class Client extends BaseEntity {
    private String nom;
    @Column(unique = true)
    private String numeroCNI;
    private Sexe sexe;
    @Column(unique = true)
    private String numeroTel;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "client")
    private Collection<CommandeClient> commandeClients;

    @Transient
    public List<Long> getCommandeClientsId(){
        return commandeClients.stream().map(CommandeClient::getId).toList();
    }

}
