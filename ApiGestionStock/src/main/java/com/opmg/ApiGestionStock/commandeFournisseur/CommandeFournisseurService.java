package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import com.opmg.ApiGestionStock.fournisseur.FournisseurService;
import com.opmg.ApiGestionStock.common.*;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.exception.InvalidOperationException;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseur;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurRequest;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurResponse;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurService;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStockRequest;
import com.opmg.ApiGestionStock.mouvementStock.MouvementStockService;
import com.opmg.ApiGestionStock.mouvementStock.TypeMouvement;
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

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeFournisseurService {
    private final CommandeFournisseurMapper mapper;
    private final CommandeFournisseurRepository repository;
    private final FournisseurService fournisseurService;
    private final LigneCommandeFournisseurService ligneCommandeFournisseurService;
    private final MouvementStockService mouvementStockService;

    public Long save(@Valid CommandeFournisseurRequest request) {
        boolean existsCode = repository.existsByCode(request.code());
        if (existsCode && request.id() == null) {
            log.error("Cade already use CODE:: {}", request.code());
            throw new InvalidEntityException(CODE_COMMANDE_FOURNISSEUR_ALREADY_EXISTS);
        }
        Fournisseur fournisseur = fournisseurService.getFournisseurById(request.fournisseur());
        CommandeFournisseur commandeFournisseur = mapper.toCommandeFournisseur(request);
        commandeFournisseur.setFournisseur(fournisseur);
        repository.save(commandeFournisseur);
        for (LigneCommandeFournisseurRequest ligne : request.ligneCommandeFournisseurs()) {
            ligneCommandeFournisseurService.save(ligne, commandeFournisseur);
        }
        return commandeFournisseur.getId();
    }

    public PageResponse<CommandeFournisseurResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CommandeFournisseur> commandeFournisseurs = repository.findAll(pageable);
        List<CommandeFournisseurResponse> commandeFournisseurResponses = commandeFournisseurs.stream().map(mapper::toCommandeFournisseurResponse).toList();
        return new PageResponse<>(
                commandeFournisseurResponses,
                commandeFournisseurs.getNumber(),
                commandeFournisseurs.getSize(),
                commandeFournisseurs.getTotalElements(),
                commandeFournisseurs.getTotalPages(),
                commandeFournisseurs.isFirst(),
                commandeFournisseurs.isLast()
        );
    }

    public CommandeFournisseurResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toCommandeFournisseurResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    public CommandeFournisseurResponse findByCode(String code) {
        return repository.findByCode(code)
                .map(mapper::toCommandeFournisseurResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    public PageResponse<LigneCommandeFournisseurResponse> findAllLigneCommandeFournisseurByCommandeFournisseur(int page, int size, Long id){
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(id);
        return ligneCommandeFournisseurService.findAllByCommandeFournisseur(page, size, commandeFournisseur);
    }

    public PageResponse<LigneCommandeFournisseurResponse> findAllLigneCommandeFournisseurByArticle(int page, int size, Long id){
        return ligneCommandeFournisseurService.findAllByArticle(page, size, id);
    }

    public Long updateEtat(@Valid ChangeEtatCommandeRequest request) {
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commandeId());
        isCommandeLivree(commandeFournisseur);
        commandeFournisseur.setEtatCommande(request.etatCommande());
        if(EtatCommande.LIVREE.equals(request.etatCommande())){
            for(Long id : commandeFournisseur.getLigneCommandeIds()){
                LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurService.getLigneCommandeFournisseurById(id);
                mouvementStockService.entreeStock(ligneCommandeFournisseur);
            }
        }
        return repository.save(commandeFournisseur).getId();
    }

    public Long updateQuantite(@Valid ChangeQuantiteCommandeRequest request) {
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commandeId());
        isCommandeLivree(commandeFournisseur);
        isLigneCommandeFournisseur(commandeFournisseur, request.ligneCommandeId());
        ligneCommandeFournisseurService.updateQuantite(request);
        return commandeFournisseur.getId();
    }

    public Long updateFournisseur(@Valid ChangeFournisseurRequest request) {
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commandeFournisseur());
        isCommandeLivree(commandeFournisseur);
        Fournisseur fournisseur = fournisseurService.getFournisseurById(request.fournisseur());
        commandeFournisseur.setFournisseur(fournisseur);
        return repository.save(commandeFournisseur).getId();
    }

    public Long updateArticle(@Valid ChangeArticleCommandeRequest request){
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commandeId());
        isCommandeLivree(commandeFournisseur);
        isLigneCommandeFournisseur(commandeFournisseur, request.ligneCommandeId());
        ligneCommandeFournisseurService.updateArticle(request);
        return commandeFournisseur.getId();
    }

    public Long updateDateCommande(@Valid ChangeDateCommandeRequest request) {
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commande());
        isCommandeLivree(commandeFournisseur);
        commandeFournisseur.setDateCommande(request.dateCommande());
        return repository.save(commandeFournisseur).getId();
    }

    public Long deleteArticle(@Valid DeleteLigneCommandeRequest request){
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(request.commande());
        isCommandeLivree(commandeFournisseur);
        isLigneCommandeFournisseur(commandeFournisseur, request.ligneCommande());
        ligneCommandeFournisseurService.delete(request);
        return commandeFournisseur.getId();
    }

    public void delete(Long id) {
        CommandeFournisseur commandeFournisseur = getCommandeFournisseurById(id);
        isCommandeLivree(commandeFournisseur);
        repository.deleteById(id);
    }

    public CommandeFournisseur getCommandeFournisseurById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    public void isCommandeLivree(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur.isCommandeLivree()) {
            log.error("Commande is not be modified");
            throw new InvalidOperationException(COMMANDE_FOURNISSEUR_CANNOT_BE_MODIFIED);
        }
    }

    public void isLigneCommandeFournisseur(CommandeFournisseur commandeFournisseur, Long ligneCommandeFournisseurId){
        LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurService.getLigneCommandeFournisseurById(ligneCommandeFournisseurId);
        if(!commandeFournisseur.getLigneCommandeFournisseurs().contains(ligneCommandeFournisseur)){
            log.error("Ligne is not be belong commande fournisseur");
            throw new InvalidOperationException(LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
    }


}
