package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.common.*;
import com.opmg.ApiGestionStock.ligneCommandeClient.LigneCommandeClientResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commandes-client")
@RequiredArgsConstructor
@Tag(name = "Commande Client")
public class CommandeClientController {
    private final CommandeClientService service;

    @PostMapping
    public ResponseEntity<Long> saveCommandeClient(@Valid @RequestBody CommandeClientRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PatchMapping("/etat/update")
    public ResponseEntity<Long> updateCommandeClientEtat(@Valid @RequestBody ChangeEtatCommandeRequest request){
        return ResponseEntity.ok(service.updateEtat(request));
    }

    @PatchMapping("/lignes-commande/quantite/update")
    public ResponseEntity<Long> updateLigneCommandeClientQuantite(@Valid @RequestBody ChangeQuantiteCommandeRequest request){
        return ResponseEntity.ok(service.updateQuantite(request));
    }

    @PatchMapping("/client/update")
    public ResponseEntity<Long> updateCommandeClientClient(@Valid @RequestBody ChangeClientRequest request){
        return ResponseEntity.ok(service.updateClient(request));
    }

    @PatchMapping("/lignes-commande/article/update")
    public ResponseEntity<Long> updateLigneCommandeClientArticle(@Valid @RequestBody ChangeArticleCommandeRequest request){
        return ResponseEntity.ok(service.updateArticle(request));
    }

    @PatchMapping("/date-commande/update")
    public ResponseEntity<Long> updateCommandeClientDateCommande(@Valid @RequestBody ChangeDateCommandeRequest request){
        return ResponseEntity.ok(service.updateDateCommande(request));
    }

    @DeleteMapping("/lignes-commande/delete")
    public ResponseEntity<Long> deleteLigneCommandeClient(@Valid @RequestBody DeleteLigneCommandeRequest request){
        return ResponseEntity.ok(service.deleteArticle(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CommandeClientResponse>> findAllCommandeClients(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeClientResponse> findCommandeClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CommandeClientResponse> findCommandeClientByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @GetMapping("/lignes-commande/commande-client/{id}")
    public ResponseEntity<PageResponse<LigneCommandeClientResponse>> findAllLigneCommandeClientsByCommandeClient(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLigneCommandeClientByCommandeClient(page, size,id));
    }

    @GetMapping("/lignes-commande/article/{id}")
    public ResponseEntity<PageResponse<LigneCommandeClientResponse>> findAllLigneCommandeClientsByArticle(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLigneCommandeClientByCommandeClient(page, size,id));
    }

    @GetMapping("/lignes-commande")
    public ResponseEntity<PageResponse<LigneCommandeClientResponse>> findLigneCommandeClients(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllLigneCommandeClient(page, size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommandeClient(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

}
