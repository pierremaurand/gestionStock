package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.common.PageResponse;
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
    public ResponseEntity<Long> save(@Valid @RequestBody CommandeFournisseurRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CommandeFournisseurResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeFournisseurResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CommandeFournisseurResponse> findByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }
}
