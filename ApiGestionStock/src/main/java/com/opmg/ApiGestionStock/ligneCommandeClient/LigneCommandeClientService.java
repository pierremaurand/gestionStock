package com.opmg.ApiGestionStock.ligneCommandeClient;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.article.ArticleService;
import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
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

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LigneCommandeClientService {
    private final LigneCommandeClientRepository repository;
    private final LigneCommandeClientMapper mapper;
    private final ArticleService articleService;

    public LigneCommandeClient save(LigneCommandeClientRequest request, CommandeClient commandeClient){
        Article article = articleService.getArticleById(request.article());
        LigneCommandeClient ligneCommandeClient = mapper.toLigneCommandeClient(request);
        ligneCommandeClient.setCommandeClient(commandeClient);
        ligneCommandeClient.setArticle(article);
        return repository.save(ligneCommandeClient);
    }

    public PageResponse<LigneCommandeClientResponse> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<LigneCommandeClient> ligneCommandeClients = repository.findAll(pageable);
        List<LigneCommandeClientResponse> ligneCommandeClientResponses = mapToResponseList(ligneCommandeClients);
        return getPages(ligneCommandeClients,ligneCommandeClientResponses);
    }

    public PageResponse<LigneCommandeClientResponse> findAllByCommandeClient(int page, int size, CommandeClient commandeClient){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<LigneCommandeClient> ligneCommandeClients = repository.findAllByIdIn(commandeClient.getLigneCommandeIds(), pageable);
        List<LigneCommandeClientResponse> ligneCommandeClientResponses = mapToResponseList(ligneCommandeClients);
        return getPages(ligneCommandeClients,ligneCommandeClientResponses);
    }

    public PageResponse<LigneCommandeClientResponse> findAllByArticle(int page, int size, Long id){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Article article = articleService.getArticleById(id);
        Page<LigneCommandeClient> ligneCommandeClients = repository.findAllByIdIn(article.getLigneCommandeClientsIds(), pageable);
        List<LigneCommandeClientResponse> ligneCommandeClientResponses = mapToResponseList(ligneCommandeClients);
        return getPages(ligneCommandeClients, ligneCommandeClientResponses);
    }

    public void updateQuantite(ChangeQuantiteCommandeRequest request) {
        LigneCommandeClient ligneCommandeClient = getLigneCommandeClientById(request.ligneCommandeId());
        ligneCommandeClient.setQuantite(request.quantite());
        repository.save(ligneCommandeClient);
    }

    public void updateArticle(ChangeArticleCommandeRequest request){
        LigneCommandeClient ligneCommandeClient = getLigneCommandeClientById(request.ligneCommandeId());
        Article article = articleService.getArticleById(request.articleId());
        ligneCommandeClient.setArticle(article);
        repository.save(ligneCommandeClient);
    }

    public List<LigneCommandeClientResponse> mapToResponseList(Page<LigneCommandeClient> ligneCommandeClients){
        return ligneCommandeClients.stream()
                .map(mapper::toLigneCommandeClientResponse)
                .toList();
    }

    public LigneCommandeClientResponse mapToResponse(LigneCommandeClient ligneCommandeClient){
        return mapper.toLigneCommandeClientResponse(ligneCommandeClient);
    }

    public LigneCommandeClient getLigneCommandeClientById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(LIGNE_COMMANDE_CLIENT_NOT_FOUND));
    }

    public void delete(DeleteLigneCommandeRequest request) {
        getLigneCommandeClientById(request.ligneCommande());
        repository.deleteById(request.ligneCommande());
    }

    private PageResponse<LigneCommandeClientResponse> getPages(Page<LigneCommandeClient> ligneCommandeClients, List<LigneCommandeClientResponse> ligneCommandeClientResponses){
        return new PageResponse<>(
                ligneCommandeClientResponses,
                ligneCommandeClients.getNumber(),
                ligneCommandeClients.getSize(),
                ligneCommandeClients.getTotalElements(),
                ligneCommandeClients.getTotalPages(),
                ligneCommandeClients.isFirst(),
                ligneCommandeClients.isLast()
        );
    }
}
