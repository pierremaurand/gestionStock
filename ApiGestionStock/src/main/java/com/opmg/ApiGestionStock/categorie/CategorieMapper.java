package com.opmg.ApiGestionStock.categorie;

import org.springframework.stereotype.Service;

@Service
public class CategorieMapper {
    public Categorie toCategorie(CategorieRequest request) {
        return Categorie.builder()
                .id(request.id())
                .code(request.code())
                .designation(request.designation())
                .build();
    }

    public CategorieResponse toCategorieResponse(Categorie categorie) {
        return CategorieResponse.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .totalArticles(categorie.getTotalArticles())
                .value(categorie.getCode()+" "+categorie.getDesignation())
                .build();
    }
}
