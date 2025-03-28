package com.opmg.ApiGestionStock.mouvementStock;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.article.ArticleService;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClient;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseur;
import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.MOUVEMENT_STOCK_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class MouvementStockService {
    private final MouvementStockRepository repository;
    private final MouvementStockMapper mapper;
    private final ArticleService articleService;

    public Long save(@Valid MouvementStockRequest request){
        Article article = articleService.getArticleById(request.article());
        MouvementStock mouvementStock = mapper.toMouvementStock(request);
        mouvementStock.setArticle(article);
        return repository.save(mouvementStock).getId();
    }

    public List<MouvementStockResponse> findAll(){
        return repository.findAll().stream()
                .map(mapper::toMouvementStockResponse)
                .toList();
    }

    public MouvementStockResponse findMouvementStockById(Long id) {
        return mapper.toMouvementStockResponse(getMouvementStockById(id));
    }

    public Double stockReelByArticle(Long id){
        Article article = articleService.getArticleById(id);
        List<MouvementStock> mouvementStocks = repository.findAllById(article.getMouvementStocksIds());

        return getSumByType(mouvementStocks,TypeMouvement.ENTREE)
                +getSumByType(mouvementStocks,TypeMouvement.CORRECTION_POS)
                -getSumByType(mouvementStocks,TypeMouvement.SORTIE)
                -getSumByType(mouvementStocks,TypeMouvement.CORRECTION_NEG);
    }

    public MouvementStock getMouvementStockById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MOUVEMENT_STOCK_NOT_FOUND));
    }

    private Double getSumByType(List<MouvementStock> mouvementStocks, TypeMouvement typeMouvement){
        return mouvementStocks.stream().filter(mouvementStock -> (mouvementStock.getTypeMouvement().equals(typeMouvement)))
                .mapToDouble(MouvementStock::getQuantite)
                .sum();
    }


    public void sortieStock(LigneVente ligneVente) {
        Article article = articleService.getArticleById(ligneVente.getArticleId());
        MouvementStock mouvementStock = MouvementStock.builder()
                .dateMouvement(LocalDate.now())
                .typeMouvement(TypeMouvement.SORTIE)
                .quantite(ligneVente.getQuantite())
                .article(article)
                .provenance(Provenance.VENTE)
                .build();
        repository.save(mouvementStock);
    }

    public void sortieStock(LigneCommandeClient ligneCommandeClient) {
        Article article = articleService.getArticleById(ligneCommandeClient.getArticleId());
        MouvementStock mouvementStock = MouvementStock.builder()
                .dateMouvement(LocalDate.now())
                .typeMouvement(TypeMouvement.SORTIE)
                .quantite(ligneCommandeClient.getQuantite())
                .article(article)
                .provenance(Provenance.COMMANDE_CLIENT)
                .build();
        repository.save(mouvementStock);
    }

    public void entreeStock(LigneCommandeFournisseur ligneCommandeFournisseur) {
        Article article = articleService.getArticleById(ligneCommandeFournisseur.getArticleId());
        MouvementStock mouvementStock = MouvementStock.builder()
                .dateMouvement(LocalDate.now())
                .typeMouvement(TypeMouvement.ENTREE)
                .quantite(ligneCommandeFournisseur.getQuantite())
                .article(article)
                .provenance(Provenance.COMMANDE_FOURNISSEUR)
                .build();
        repository.save(mouvementStock);
    }
}
