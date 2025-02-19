package com.opmg.ApiGestionStock.vente;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.ligneVente.LigneVenteResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("ventes")
@Tag(name = "Vente")
public class VenteController {
    private final VenteService service;

    @PostMapping
    public ResponseEntity<Long> saveVente(@Valid @RequestBody VenteRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<VenteResponse>> findAllVentes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/lignes-vente")
    public ResponseEntity<PageResponse<LigneVenteResponse>> findAllLigneVentes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllLignesVente(page, size));
    }

    @GetMapping("/lignes-vente/vente/{id}")
    public ResponseEntity<PageResponse<LigneVenteResponse>> findAllLigneVentesByVente(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLignesVenteByVente(page, size, id));
    }

    @GetMapping("/lignes-vente/article/{id}")
    public ResponseEntity<PageResponse<LigneVenteResponse>> findAllLigneVentesByArticle(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllLignesVenteByArticle(page, size, id));
    }
}
