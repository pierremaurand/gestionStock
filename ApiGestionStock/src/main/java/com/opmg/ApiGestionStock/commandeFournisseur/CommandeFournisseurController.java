package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.common.*;
import com.opmg.ApiGestionStock.ligneCommandeFournisseur.LigneCommandeFournisseurResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("commandes-fournisseur")
@Tag(name = "Commande Fournisseur")
@RequiredArgsConstructor
public class CommandeFournisseurController {
    private final CommandeFournisseurService service;

    @PostMapping
    public ResponseEntity<Long> saveCommandeFournisseur(@Valid @RequestBody CommandeFournisseurRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PatchMapping("/etat/update")
    public ResponseEntity<Long> updateCommandeFournisseurEtat(@Valid @RequestBody ChangeEtatCommandeRequest request){
        return ResponseEntity.ok(service.updateEtat(request));
    }

    @PatchMapping("/lignes-commande/quantite/update")
    public ResponseEntity<Long> updateLigneCommandeFournisseurQuantite(@Valid @RequestBody ChangeQuantiteCommandeRequest request){
        return ResponseEntity.ok(service.updateQuantite(request));
    }

    @PatchMapping("/fournisseur/update")
    public ResponseEntity<Long> updateCommandeFournisseurFournisseur(@Valid @RequestBody ChangeFournisseurRequest request){
        return ResponseEntity.ok(service.updateFournisseur(request));
    }

    @PatchMapping("/lignes-commande/article/update")
    public ResponseEntity<Long> updateLigneCommandeFournisseurArticle(@Valid @RequestBody ChangeArticleCommandeRequest request){
        return ResponseEntity.ok(service.updateArticle(request));
    }

    @PatchMapping("/date-commande/update")
    public ResponseEntity<Long> updateCommandeFournisseurDateCommande(@Valid @RequestBody ChangeDateCommandeRequest request){
        return ResponseEntity.ok(service.updateDateCommande(request));
    }

    @DeleteMapping("/lignes-commande/delete")
    public ResponseEntity<Long> deleteLigneCommandeFournisseur(@Valid @RequestBody DeleteLigneCommandeRequest request){
        return ResponseEntity.ok(service.deleteArticle(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CommandeFournisseurResponse>> findAllCommandeFournisseurs(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeFournisseurResponse> findCommandeFournisseurById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CommandeFournisseurResponse> findCommandeFournisseurByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @GetMapping("/lignes-commande/commande-fournisseur/{id}")
    public ResponseEntity<PageResponse<LigneCommandeFournisseurResponse>> findAllLigneCommandeFournisseursByCommandeFournisseur(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLigneCommandeFournisseurByCommandeFournisseur(page, size,id));
    }

    @GetMapping("/lignes-commande/article/{id}")
    public ResponseEntity<PageResponse<LigneCommandeFournisseurResponse>> findAllLigneCommandeFournisseursByArticle(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLigneCommandeFournisseurByArticle(page, size,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommandeFournisseur(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
