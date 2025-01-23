package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.fournisseur.FournisseurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommandeFournisseurMapper {
    private final FournisseurMapper fournisseurMapper;

    public CommandeFournisseur toCommandeFournisseur(CommandeFournisseurRequest request) {
        return CommandeFournisseur.builder()
                .code(request.code())
                .dateCommande(request.dateCommande())
                .etatCommande(request.etatCommande())
                .build();
    }

    public CommandeFournisseurResponse toCommandeFournisseurResponse(CommandeFournisseur commandeFournisseur) {
        return CommandeFournisseurResponse.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .fournisseur(fournisseurMapper.toFournisseurResponse(commandeFournisseur.getFournisseur()))
                .build();
    }
}
