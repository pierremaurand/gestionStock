package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapper {
    public Article toArticle(ArticleRequest request) {
        return Article.builder()
                .code(request.code())
                .designation(request.designation())
                .prixUnitaireHt(request.prixUnitaireHt())
                .tauxTva(request.tauxTva())
                .build();
    }

    public ArticleResponse toArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .code(article.getCode())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .categorie(article.getCategorieId())
                .photo(FileUtils.readFileFromLocation(article.getPhoto()))
                .build();
    }
}
