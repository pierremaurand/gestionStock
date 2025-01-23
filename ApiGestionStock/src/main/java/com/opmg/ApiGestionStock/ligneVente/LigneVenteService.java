package com.opmg.ApiGestionStock.ligneVente;

import com.opmg.ApiGestionStock.article.Article;
import com.opmg.ApiGestionStock.article.ArticleService;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.vente.Vente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LigneVenteService {
    private final LigneVenteRepository repository;
    private final LigneVenteMapper mapper;
    private final ArticleService articleService;

    public LigneVente save(@Valid LigneVenteRequest request, Vente vente){
        Article article = articleService.getArticleById(request.article());
        LigneVente ligneVente = mapper.toLigneVente(request);
        ligneVente.setVente(vente);
        ligneVente.setArticle(article);
        return repository.save(ligneVente);
    }

    public PageResponse<LigneVenteResponse> findAllByArticle(int page, int size, Long id) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Article article = articleService.getArticleById(id);
        Page<LigneVente> ligneVentes = repository.findAllByIdIn(article.getLigneVentesIds(),pageable);
        List<LigneVenteResponse> ligneVenteResponses = ligneVentes.stream().map(mapper::toLigneVenteResponse).toList();
        return new PageResponse<>(
                ligneVenteResponses,
                ligneVentes.getNumber(),
                ligneVentes.getSize(),
                ligneVentes.getTotalElements(),
                ligneVentes.isFirst(),
                ligneVentes.isLast()
        );
    }

    public PageResponse<LigneVenteResponse> findAllByVente(int page, int size, Vente vente) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<LigneVente> ligneVentes = repository.findAllByIdIn(vente.getLigneVentesIds(),pageable);
        List<LigneVenteResponse> ligneVenteResponses = ligneVentes.stream().map(mapper::toLigneVenteResponse).toList();
        return new PageResponse<>(
                ligneVenteResponses,
                ligneVentes.getNumber(),
                ligneVentes.getSize(),
                ligneVentes.getTotalElements(),
                ligneVentes.isFirst(),
                ligneVentes.isLast()
        );
    }

    public PageResponse<LigneVenteResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<LigneVente> ligneVentes = repository.findAll(pageable);
        List<LigneVenteResponse> ligneVenteResponses = ligneVentes.stream().map(mapper::toLigneVenteResponse).toList();
        return new PageResponse<>(
                ligneVenteResponses,
                ligneVentes.getNumber(),
                ligneVentes.getSize(),
                ligneVentes.getTotalElements(),
                ligneVentes.isFirst(),
                ligneVentes.isLast()
        );
    }
}
