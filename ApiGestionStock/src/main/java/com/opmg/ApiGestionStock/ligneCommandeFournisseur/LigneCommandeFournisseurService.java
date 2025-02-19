package com.opmg.ApiGestionStock.ligneCommandeFournisseur;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.article.ArticleService;
import com.opmg.ApiGestionStock.commandeFournisseur.CommandeFournisseur;
import com.opmg.ApiGestionStock.common.ChangeArticleCommandeRequest;
import com.opmg.ApiGestionStock.common.ChangeQuantiteCommandeRequest;
import com.opmg.ApiGestionStock.common.DeleteLigneCommandeRequest;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LigneCommandeFournisseurService {
    private final LigneCommandeFournisseurRepository repository;
    private final LigneCommandeFournisseurMapper mapper;
    private final ArticleService articleService;

    public LigneCommandeFournisseur save(LigneCommandeFournisseurRequest request, CommandeFournisseur commandeFournisseur){
        Article article = articleService.getArticleById(request.article());
        LigneCommandeFournisseur ligneCommandeFournisseur = mapper.toLigneCommandeFournisseur(request);
        ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
        ligneCommandeFournisseur.setArticle(article);
        return repository.save(ligneCommandeFournisseur);
    }

    public PageResponse<LigneCommandeFournisseurResponse> findAllByCommandeFournisseur(int page, int size, CommandeFournisseur commandeFournisseur){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<LigneCommandeFournisseur> ligneCommandeFournisseurs = repository.findAllByIdIn(commandeFournisseur.getLigneCommandeIds(), pageable);
        List<LigneCommandeFournisseurResponse> ligneCommandeFournisseurResponses = mapToResponseList(ligneCommandeFournisseurs);
        return new PageResponse<>(
                ligneCommandeFournisseurResponses,
                ligneCommandeFournisseurs.getNumber(),
                ligneCommandeFournisseurs.getSize(),
                ligneCommandeFournisseurs.getTotalElements(),
                ligneCommandeFournisseurs.getTotalPages(),
                ligneCommandeFournisseurs.isFirst(),
                ligneCommandeFournisseurs.isLast()
        );
    }

    public PageResponse<LigneCommandeFournisseurResponse> findAllByArticle(int page, int size, Long id){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Article article = articleService.getArticleById(id);
        Page<LigneCommandeFournisseur> ligneCommandeFournisseurs = repository.findAllByIdIn(article.getLigneCommandeFournisseursIds(), pageable);
        List<LigneCommandeFournisseurResponse> ligneCommandeFournisseurResponses = mapToResponseList(ligneCommandeFournisseurs);
        return new PageResponse<>(
                ligneCommandeFournisseurResponses,
                ligneCommandeFournisseurs.getNumber(),
                ligneCommandeFournisseurs.getSize(),
                ligneCommandeFournisseurs.getTotalElements(),
                ligneCommandeFournisseurs.getTotalPages(),
                ligneCommandeFournisseurs.isFirst(),
                ligneCommandeFournisseurs.isLast()
        );
    }

    public void updateQuantite(ChangeQuantiteCommandeRequest request) {
        LigneCommandeFournisseur ligneCommandeFournisseur = getLigneCommandeFournisseurById(request.ligneCommandeId());
        ligneCommandeFournisseur.setQuantite(request.quantite());
        repository.save(ligneCommandeFournisseur);
    }

    public void updateArticle(ChangeArticleCommandeRequest request){
        LigneCommandeFournisseur ligneCommandeFournisseur = getLigneCommandeFournisseurById(request.ligneCommandeId());
        Article article = articleService.getArticleById(request.articleId());
        ligneCommandeFournisseur.setArticle(article);
        repository.save(ligneCommandeFournisseur);
    }

    public List<LigneCommandeFournisseurResponse> mapToResponseList(Page<LigneCommandeFournisseur> ligneCommandeFournisseurs){
        return ligneCommandeFournisseurs.stream()
                .map(mapper::toLigneCommandeFournisseurResponse)
                .toList();
    }

    public LigneCommandeFournisseurResponse mapToResponse(LigneCommandeFournisseur ligneCommandeFournisseur){
        return mapper.toLigneCommandeFournisseurResponse(ligneCommandeFournisseur);
    }

    public LigneCommandeFournisseur getLigneCommandeFournisseurById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    public void delete(DeleteLigneCommandeRequest request) {
        getLigneCommandeFournisseurById(request.ligneCommande());
        repository.deleteById(request.ligneCommande());
    }
}
