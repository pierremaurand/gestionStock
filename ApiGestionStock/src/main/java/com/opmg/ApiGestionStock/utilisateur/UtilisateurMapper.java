package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.AdresseMapper;
import com.opmg.ApiGestionStock.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurMapper {
    private final AdresseMapper adresseMapper;

    public Utilisateur toUtilisateur(UtilisateurRequest request){
        return Utilisateur.builder()
                .id(request.id())
                .nom(request.nom())
                .login(request.login())
                .sexe(request.sexe())
                .numeroTel(request.numeroTel())
                .email(request.email())
                .adresse(adresseMapper.toAdresse(request.adresse()))
                .build();
    }

    public UtilisateurResponse toUtilisateurResponse(Utilisateur utilisateur){
        return UtilisateurResponse.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .login(utilisateur.getLogin())
                .sexe(utilisateur.getSexe())
                .numeroTel(utilisateur.getNumeroTel())
                .email(utilisateur.getEmail())
                .photo(FileUtils.readFileFromLocation(utilisateur.getPhoto()))
                .adresse(adresseMapper.toAdresseResponse(utilisateur.getAdresse()))
                .build();
    }
}
