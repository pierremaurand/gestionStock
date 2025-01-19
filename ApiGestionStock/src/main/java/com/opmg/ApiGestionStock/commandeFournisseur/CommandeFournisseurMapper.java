package com.opmg.ApiGestionStock.commandeFournisseur;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommandeFournisseurMapper {
    public CommandeFournisseur toCommandeFournisseur(CommandeFournisseurRequest request) {
        return CommandeFournisseur.builder()
                .id(request.id())
                .code(request.code())
                .dateCommande(LocalDate.parse(request.dateCommande()))
                .build();
    }

    public CommandeFournisseurResponse toCommandeFournisseurResponse(CommandeFournisseur commandeFournisseur) {
        return CommandeFournisseurResponse.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(commandeFournisseur.getFournisseurId())
                .build();
    }
}
