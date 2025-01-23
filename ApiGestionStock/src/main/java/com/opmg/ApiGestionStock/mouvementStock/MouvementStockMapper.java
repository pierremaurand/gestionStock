package com.opmg.ApiGestionStock.mouvementStock;

import org.springframework.stereotype.Service;

@Service
public class MouvementStockMapper {
    public MouvementStock toMouvementStock(MouvementStockRequest request){
        return MouvementStock.builder()
                .dateMouvement(request.dateMouvement())
                .typeMouvement(request.typeMouvement())
                .quantite(request.quantite())
                .build();
    }

    public MouvementStockResponse toMouvementStockResponse(MouvementStock mouvementStock){
        return MouvementStockResponse.builder()
                .id(mouvementStock.getId())
                .dateMouvement(mouvementStock.getDateMouvement())
                .typeMouvement(mouvementStock.getTypeMouvement())
                .quantite(mouvementStock.getQuantite())
                .article(mouvementStock.getArticleId())
                .build();
    }
}
