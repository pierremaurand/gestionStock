package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.fournisseur.FournisseurMapper;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommandeFournisseurMapper {
    private final FournisseurMapper fournisseurMapper;
    private final LigneCommandeFournisseurMapper ligneCommandeFournisseurMapper;

    public CommandeFournisseur toCommandeFournisseur(CommandeFournisseurRequest request) {
        return CommandeFournisseur.builder()
                .id(request.id())
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
                .ligneCommandeFournisseurs(commandeFournisseur.getLigneCommandeFournisseurs().stream().map(ligneCommandeFournisseurMapper::toLigneCommandeFournisseurResponse).toList())
                .build();
    }
}
