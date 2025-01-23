package com.opmg.ApiGestionStock.fournisseur;

import org.springframework.stereotype.Service;

@Service
public class FournisseurMapper {
    public Fournisseur toFournisseur(FournisseurRequest request){
        return Fournisseur.builder()
                .nom(request.nom())
                .sexe(request.sexe())
                .numeroCNI(request.numeroCNI())
                .numeroTel(request.numeroTel())
                .email(request.email())
                .build();
    }

    public FournisseurResponse toFournisseurResponse(Fournisseur fournisseur){
        return FournisseurResponse.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .sexe(fournisseur.getSexe())
                .numeroCNI(fournisseur.getNumeroCNI())
                .numeroTel(fournisseur.getNumeroTel())
                .email(fournisseur.getEmail())
                .commandeFournisseurs(fournisseur.getCommandeFournisseursId())
                .build();
    }
}
