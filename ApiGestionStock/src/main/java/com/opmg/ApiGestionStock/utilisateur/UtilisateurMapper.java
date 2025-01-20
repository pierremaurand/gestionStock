package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.role.Role;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {
    public Utilisateur toUtilisateur(UtilisateurRequest request){
        return Utilisateur.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .build();
    }
}
