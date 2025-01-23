package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import com.opmg.ApiGestionStock.article.ArticleMapper;
import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LigneCommandeFournisseurMapper {
    private final ArticleMapper articleMapper;
    private final CommandeFournisseurMapper commandeFournisseurMapper;

    public LigneCommandeFournisseur toLigneCommandeFournisseur(LigneCommandeFournisseurRequest request){
        return LigneCommandeFournisseur.builder()
                .quantite(request.quantite())
                .prixUnitaire(request.prixUnitaire())
                .build();
    }

    public LigneCommandeFournisseurResponse toLigneCommandeFournisseurResponse(LigneCommandeFournisseur ligneCommandeFournisseur){
        return LigneCommandeFournisseurResponse.builder()
                .id(ligneCommandeFournisseur.getId())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .article(articleMapper.toArticleResponse(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur(commandeFournisseurMapper.toCommandeFournisseurResponse(ligneCommandeFournisseur.getCommandeFournisseur()))
                .build();
    }
}
