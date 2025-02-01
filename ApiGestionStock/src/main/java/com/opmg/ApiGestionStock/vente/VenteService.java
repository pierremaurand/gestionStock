package com.opmg.ApiGestionStock.vente;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.ligneVente.LigneVente;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteRequest;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteResponse;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteService;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.VENTE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class VenteService {
    private final VenteRepository repository;
    private final VenteMapper mapper;
    private final MouvementStockService mouvementStockService;
    private final LigneVenteService ligneVenteService;

    public Long save(VenteRequest request){
        Vente vente = mapper.toVente(request);
        repository.save(vente);
        for(LigneVenteRequest ligne: request.ligneVentes()){
            LigneVente ligneVente = ligneVenteService.save(ligne, vente);
            mouvementStockService.sortieStock(ligneVente);
        }
        return vente.getId();
    }

    public VenteResponse findById(Long id){
        return mapper.toVenteResponse(getVenteById(id));
    }

    public VenteResponse findByCode(String code){
        return mapper.toVenteResponse(getVenteByCode(code));
    }

    public PageResponse<LigneVenteResponse> findAllLignesVente(int page, int size){
        return ligneVenteService.findAll(page, size);
    }

    public PageResponse<LigneVenteResponse> findAllLignesVenteByArticle(int page, int size, Long id){
        return ligneVenteService.findAllByArticle(page, size, id);
    }

    public PageResponse<LigneVenteResponse> findAllLignesVenteByVente(int page, int size, Long id){
        Vente vente = getVenteById(id);
        return ligneVenteService.findAllByVente(page, size, vente);
    }

    public PageResponse<VenteResponse> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Vente> ventes = repository.findAll(pageable);
        List<VenteResponse> venteResponses = ventes.stream().map(mapper::toVenteResponse).toList();
        return new PageResponse<>(
                venteResponses,
                ventes.getNumber(),
                ventes.getSize(),
                ventes.getTotalElements(),
                ventes.getTotalPages(),
                ventes.isFirst(),
                ventes.isLast()
        );
    }

    public Vente getVenteById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(VENTE_NOT_FOUND));
    }

    public Vente getVenteByCode(String code){
        return repository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(VENTE_NOT_FOUND));
    }
}
