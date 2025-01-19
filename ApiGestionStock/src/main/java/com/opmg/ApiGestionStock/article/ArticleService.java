package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.categorie.CategorieRepository;
import com.opmg.ApiGestionStock.categorie.CategorieRequest;
import com.opmg.ApiGestionStock.categorie.CategorieResponse;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.ARTICLE_NOT_FOUND;
import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.CATEGORIE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategorieRepository categorieRepository;
    private final FileStorageService fileStorageService;
    private final ArticleMapper articleMapper;

    public Long save(ArticleRequest request) {
        boolean exists = articleRepository.existsByCode(request.code());
        if (exists) {
            log.error("Article already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.CODE_ARTICLE_ALREADY_EXISTS);
        }
        Categorie categorie = categorieRepository.findById(request.categorieId())
                .orElseThrow(() -> new EntityNotFoundException(CATEGORIE_NOT_FOUND));
        Article article = articleMapper.toArticle(request);
        article.setCategorie(categorie);
        return articleRepository.save(article).getId();
    }

    public PageResponse<ArticleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Article> articles = articleRepository.findAll(pageable);
        List<ArticleResponse> articlesResponse = articles.stream().map(articleMapper::toArticleResponse).toList();
        return new PageResponse<>(
                articlesResponse,
                articles.getNumber(),
                articles.getSize(),
                articles.getTotalElements(),
                articles.isFirst(),
                articles.isLast()
        );
    }

    public ArticleResponse findById(Long articleId) {
        return articleRepository.findById(articleId)
                .map(articleMapper::toArticleResponse)
                .orElseThrow(() -> new EntityNotFoundException(ARTICLE_NOT_FOUND));
    }

    public ArticleResponse findByCode(String code) {
        return articleRepository.findByCode(code)
                .map(articleMapper::toArticleResponse)
                .orElseThrow(() -> new EntityNotFoundException(ARTICLE_NOT_FOUND));
    }

    public void uploadPicture(MultipartFile file, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow();
        var photo = fileStorageService.saveFile(file, "article", String.valueOf(articleId));
        article.setPhoto(photo);
        articleRepository.save(article);
    }
}
