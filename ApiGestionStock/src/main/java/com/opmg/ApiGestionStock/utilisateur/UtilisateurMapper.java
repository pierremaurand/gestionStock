package com.opmg.ApiGestionStock.utilisateur;

import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {
    public Utilisateur toUtilisateur(UtilisateurRequest request){
        return Utilisateur.builder()
                .nom(request.nom())
                .login(request.login())
                .build();
    }

    public UtilisateurResponse toUtilisateurResponse(Utilisateur utilisateur){
        return UtilisateurResponse.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .login(utilisateur.getLogin())
                .sexe(utilisateur.getSexe())
                .build();
    }
}
