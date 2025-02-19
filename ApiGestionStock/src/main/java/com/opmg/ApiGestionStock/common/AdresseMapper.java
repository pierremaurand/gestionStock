package com.opmg.ApiGestionStock.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdresseMapper {
    public Adresse toAdresse(AdresseRequest request){
        return Adresse.builder()
                .adresse1(request.adresse1())
                .adresse2(request.adresse2())
                .ville(request.ville())
                .codePostale(request.codePostale())
                .pays(request.pays())
                .build();
    }

    public AdresseResponse toAdresseResponse(Adresse adresse) {
        if(adresse == null) {
            return null;
        }
        return AdresseResponse.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostale(adresse.getCodePostale())
                .pays(adresse.getPays())
                .build();
    }
}
