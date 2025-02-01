package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.categorie.CategorieService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository repository;
    private final ArticleMapper mapper;
    private final FileStorageService fileStorageService;
    private final CategorieService categorieService;

    public Long save(ArticleRequest request) {
        if (repository.existsByCode(request.code())) {
            log.error("Article already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.CODE_ARTICLE_ALREADY_EXISTS);
        }
        Categorie categorie = categorieService.getCategorieById(request.categorie());
        Article article = mapper.toArticle(request);
        article.setCategorie(categorie);
        return repository.save(article).getId();
    }

    public PageResponse<ArticleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Article> articles = repository.findAll(pageable);
        List<ArticleResponse> articlesResponse = articles.stream().map(mapper::toArticleResponse).toList();
        return new PageResponse<>(
                articlesResponse,
                articles.getNumber(),
                articles.getSize(),
                articles.getTotalElements(),
                articles.getTotalPages(),
                articles.isFirst(),
                articles.isLast()
        );
    }

    public ArticleResponse findById(Long id) {
        return mapper.toArticleResponse(getArticleById(id));
    }

    public ArticleResponse findByCode(String code) {
        return mapper.toArticleResponse(getArticleByCode(code));
    }

    public void savePhoto(MultipartFile file, Long id) {
        Article article = getArticleById(id);
        var photo = fileStorageService.saveFile(file, "article", String.valueOf(id));
        article.setPhoto(photo);
        repository.save(article);
    }

    public PageResponse<ArticleResponse> findAllByCategorieId(int page, int size, Long id){
        Categorie categorie = categorieService.getCategorieById(id);
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Article> articles = repository.findAllByIdIn(categorie.getArticlesIds(), pageable);
        List<ArticleResponse> articleResponses = articles.stream().map(mapper::toArticleResponse).toList();
        return new PageResponse<>(
                articleResponses,
                articles.getNumber(),
                articles.getSize(),
                articles.getTotalElements(),
                articles.getTotalPages(),
                articles.isFirst(),
                articles.isLast()
        );
    }

    public void delete(Long id){
        Article article = getArticleById(id);
        if(article.getMouvementStocksIds().isEmpty()
        && article.getLigneCommandeClientsIds().isEmpty()
        && article.getLigneCommandeFournisseursIds().isEmpty()
        && article.getLigneVentes().isEmpty()){
            repository.deleteById(id);
        }
    }

    public Article getArticleById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ARTICLE_NOT_FOUND));
    }

    public Article getArticleByCode(String code){
        return repository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(ARTICLE_NOT_FOUND));
    }


}
