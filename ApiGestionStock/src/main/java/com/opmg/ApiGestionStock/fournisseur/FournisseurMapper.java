package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.AdresseMapper;
import com.opmg.ApiGestionStock.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FournisseurMapper {
    private final AdresseMapper adresseMapper;

    public Fournisseur toFournisseur(FournisseurRequest request){
        return Fournisseur.builder()
                .id(request.id())
                .nom(request.nom())
                .sexe(request.sexe())
                .numeroCNI(request.numeroCNI())
                .numeroTel(request.numeroTel())
                .email(request.email())
                .adresse(adresseMapper.toAdresse(request.adresse()))
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
                .photo(FileUtils.readFileFromLocation(fournisseur.getPhoto()))
                .adresse(adresseMapper.toAdresseResponse(fournisseur.getAdresse()))
                .build();
    }
}
