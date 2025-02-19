package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.client.Client;
import com.opmg.ApiGestionStock.client.ClientService;
import com.opmg.ApiGestionStock.common.*;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.exception.InvalidOperationException;
import com.opmg.ApiGestionStock.ligneCommandeClient.*;
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
public class CommandeClientService {
    private final CommandeClientMapper mapper;
    private final CommandeClientRepository repository;
    private final ClientService clientService;
    private final LigneCommandeClientService ligneCommandeClientService;
    private final MouvementStockService mouvementStockService;

    public Long save(@Valid CommandeClientRequest request) {
        boolean existsCode = repository.existsByCode(request.code());
        if (existsCode && request.id() == null) {
            log.error("Cade already use CODE:: {}", request.code());
            throw new InvalidEntityException(CODE_COMMANDE_CLIENT_ALREADY_EXISTS);
        }
        Client client = clientService.getClientById(request.client());
        CommandeClient commandeClient = mapper.toCommandeClient(request);
        commandeClient.setClient(client);
        repository.save(commandeClient);
        for (LigneCommandeClientRequest ligne : request.ligneCommandeClients()) {
            ligneCommandeClientService.save(ligne, commandeClient);
        }
        return commandeClient.getId();
    }

    public PageResponse<CommandeClientResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CommandeClient> commandeClients = repository.findAll(pageable);
        List<CommandeClientResponse> commandeClientResponses = commandeClients.stream().map(mapper::toCommandeClientResponse).toList();
        return new PageResponse<>(
                commandeClientResponses,
                commandeClients.getNumber(),
                commandeClients.getSize(),
                commandeClients.getTotalElements(),
                commandeClients.getTotalPages(),
                commandeClients.isFirst(),
                commandeClients.isLast()
        );
    }

    public CommandeClientResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toCommandeClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_CLIENT_NOT_FOUND));
    }

    public CommandeClientResponse findByCode(String code) {
        return repository.findByCode(code)
                .map(mapper::toCommandeClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_CLIENT_NOT_FOUND));
    }

    public PageResponse<LigneCommandeClientResponse> findAllLigneCommandeClient(int page, int size){
       return ligneCommandeClientService.findAll(page, size);
    }

    public PageResponse<LigneCommandeClientResponse> findAllLigneCommandeClientByArticle(int page, int size, Long id){
        return ligneCommandeClientService.findAllByArticle(page, size, id);
    }

    public PageResponse<LigneCommandeClientResponse> findAllLigneCommandeClientByCommandeClient(int page, int size, Long id){
        CommandeClient commandeClient = getCommandeClientById(id);
        return ligneCommandeClientService.findAllByCommandeClient(page, size, commandeClient);
    }

    public Long updateEtat(@Valid ChangeEtatCommandeRequest request) {
        CommandeClient commandeClient = getCommandeClientById(request.commandeId());
        isCommandeLivree(commandeClient);
        commandeClient.setEtatCommande(request.etatCommande());
        if(EtatCommande.LIVREE.equals(request.etatCommande())){
            for(Long id : commandeClient.getLigneCommandeIds()){
                LigneCommandeClient ligneCommandeClient = ligneCommandeClientService.getLigneCommandeClientById(id);
                mouvementStockService.sortieStock(ligneCommandeClient);
            }
        }
        return repository.save(commandeClient).getId();
    }

    public Long updateQuantite(@Valid ChangeQuantiteCommandeRequest request) {
        CommandeClient commandeClient = getCommandeClientById(request.commandeId());
        isCommandeLivree(commandeClient);
        isLigneCommandeClient(commandeClient, request.ligneCommandeId());
        ligneCommandeClientService.updateQuantite(request);
        return commandeClient.getId();
    }

    public Long updateClient(@Valid ChangeClientRequest request) {
        CommandeClient commandeClient = getCommandeClientById(request.commandeClient());
        isCommandeLivree(commandeClient);
        Client client = clientService.getClientById(request.client());
        commandeClient.setClient(client);
        return repository.save(commandeClient).getId();
    }

    public Long updateArticle(@Valid ChangeArticleCommandeRequest request){
        CommandeClient commandeClient = getCommandeClientById(request.commandeId());
        isCommandeLivree(commandeClient);
        isLigneCommandeClient(commandeClient, request.ligneCommandeId());
        ligneCommandeClientService.updateArticle(request);
        return commandeClient.getId();
    }

    public Long updateDateCommande(@Valid ChangeDateCommandeRequest request) {
        CommandeClient commandeClient = getCommandeClientById(request.commande());
        isCommandeLivree(commandeClient);
        commandeClient.setDateCommande(request.dateCommande());
        return repository.save(commandeClient).getId();
    }

    public Long deleteArticle(@Valid DeleteLigneCommandeRequest request){
        CommandeClient commandeClient = getCommandeClientById(request.commande());
        isCommandeLivree(commandeClient);
        isLigneCommandeClient(commandeClient, request.ligneCommande());
        ligneCommandeClientService.delete(request);
        return commandeClient.getId();
    }

    public void delete(Long id) {
        CommandeClient commandeClient = getCommandeClientById(id);
        isCommandeLivree(commandeClient);
        repository.deleteById(id);
    }

    public CommandeClient getCommandeClientById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_CLIENT_NOT_FOUND));
    }

    public void isCommandeLivree(CommandeClient commandeClient){
        if(commandeClient.isCommandeLivree()) {
            log.error("Commande is not be modified");
            throw new InvalidOperationException(COMMANDE_CLIENT_CANNOT_BE_MODIFIED);
        }
    }

    public void isLigneCommandeClient(CommandeClient commandeClient, Long ligneCommandeClientId){
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientService.getLigneCommandeClientById(ligneCommandeClientId);
        if(!commandeClient.getLigneCommandeClients().contains(ligneCommandeClient)){
            log.error("Ligne is not be belong commande client");
            throw new InvalidOperationException(LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
    }


}
