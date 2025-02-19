package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.categorie.CategorieService;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.exception.InvalidOperationException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStock;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStockMapper;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStockResponse;
import com.opmg.ApiGestionStock.mouvementStock.TypeMouvement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository repository;
    private final ArticleMapper mapper;
    private final FileStorageService fileStorageService;
    private final CategorieService categorieService;
    private final MouvementStockMapper mouvementStockMapper;

    public Long save(ArticleRequest request) {
        if (repository.existsByCode(request.code()) && request.id() == null) {
            log.error("Article already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.CODE_ARTICLE_ALREADY_EXISTS);
        }

        Categorie categorie = categorieService.getCategorieById(request.categorie());
        Article article = mapper.toArticle(request);

        if(request.id() != null) {
            article = getArticleById(request.id());
            article.setCode(request.code());
            article.setDesignation(request.designation());
            article.setPrixUnitaireHt(request.prixUnitaireHt());
            article.setTauxTva(request.tauxTva());
        }
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

    public List<ArticleResponse> findAllArticlesList() {
        return repository.findAll().stream()
                .map(mapper::toArticleResponse)
                .toList();
    }

    public List<MouvementStockResponse> findAllMouvementStock(Long articleId){
        return getArticleById(articleId).getMouvementStocks().stream()
                .map(mouvementStockMapper::toMouvementStockResponse)
                .toList();
    }

    public Double stockReel(Long id){
        Article article = getArticleById(id);
        List<MouvementStock> mouvementStocks = article.getMouvementStocks().stream().toList();

        return getSumByType(mouvementStocks,TypeMouvement.ENTREE)
                +getSumByType(mouvementStocks,TypeMouvement.CORRECTION_POS)
                -getSumByType(mouvementStocks,TypeMouvement.SORTIE)
                -getSumByType(mouvementStocks,TypeMouvement.CORRECTION_NEG);
    }

    private Double getSumByType(List<MouvementStock> mouvementStocks, TypeMouvement typeMouvement){
        return mouvementStocks.stream().filter(mouvementStock -> (mouvementStock.getTypeMouvement().equals(typeMouvement)))
                .mapToDouble(MouvementStock::getQuantite)
                .sum();
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
        } else {
            throw new InvalidOperationException(ARTICLE_OPERATION_NOT_PERMIT);
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
