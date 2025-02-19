package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.CategorieMapper;
import com.opmg.ApiGestionStock.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final CategorieMapper categorieMapper;

    public Article toArticle(ArticleRequest request) {
        return Article.builder()
                .id(request.id())
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
                .categorie(categorieMapper.toCategorieResponse(article.getCategorie()))
                .photo(FileUtils.readFileFromLocation(article.getPhoto()))
                .build();
    }
}
