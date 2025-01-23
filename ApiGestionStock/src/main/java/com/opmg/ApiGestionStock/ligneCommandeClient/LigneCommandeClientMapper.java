package com.opmg.ApiGestionStock.ligneCommandeClient;

import com.opmg.ApiGestionStock.article.ArticleMapper;
import com.opmg.ApiGestionStock.commandeClient.CommandeClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LigneCommandeClientMapper {
    private final ArticleMapper articleMapper;
    private final CommandeClientMapper commandeClientMapper;

    public LigneCommandeClient toLigneCommandeClient(LigneCommandeClientRequest request){
        return LigneCommandeClient.builder()
                .quantite(request.quantite())
                .prixUnitaire(request.prixUnitaire())
                .build();
    }

    public LigneCommandeClientResponse toLigneCommandeClientResponse(LigneCommandeClient ligneCommandeClient){
        return LigneCommandeClientResponse.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .article(articleMapper.toArticleResponse(ligneCommandeClient.getArticle()))
                .commandeClient(commandeClientMapper.toCommandeClientResponse(ligneCommandeClient.getCommandeClient()))
                .build();
    }
}
