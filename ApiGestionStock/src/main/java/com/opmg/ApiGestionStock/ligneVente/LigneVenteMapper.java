package com.opmg.ApiGestionStock.ligneVente;

import com.opmg.ApiGestionStock.article.ArticleMapper;
import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteRequest;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteResponse;
import com.opmg.ApiGestionStock.vente.VenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LigneVenteMapper {
    private final ArticleMapper articleMapper;
    private final VenteMapper venteMapper;

    public LigneVente toLigneVente(LigneVenteRequest request){
        return LigneVente.builder()
                .id(request.id())
                .quantite(request.quantite())
                .prixUnitaire(request.prixUnitaire())
                .build();
    }

    public LigneVenteResponse toLigneVenteResponse(LigneVente ligneVente){
        return LigneVenteResponse.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .article(articleMapper.toArticleResponse(ligneVente.getArticle()))
                .vente(venteMapper.toVenteResponse(ligneVente.getVente()))
                .build();
    }
}
