package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("fournisseurs")
@Tag(name = "Fournisseur")
@RequiredArgsConstructor
public class FournisseurController {
    private final FournisseurService service;

    @PostMapping
    public ResponseEntity<Long> saveFournisseur(@Validated @RequestBody FournisseurRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<FournisseurResponse>> findAllFournisseurs(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FournisseurResponse>> findAllFournisseursList() {
        return ResponseEntity.ok(service.findAllFournisseursList());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FournisseurResponse> findFournisseurById(
            @PathVariable("id") Long fournisseurId
    ) {
        return ResponseEntity.ok(service.findById(fournisseurId));
    }

    @GetMapping("/numero-cni/{numero-cni}")
    public ResponseEntity<FournisseurResponse> findFournisseurByNumeroCNI(
            @PathVariable("numero-cni") String fournisseurNumeroCNI
    ) {
        return ResponseEntity.ok(service.findByNumeroCNI(fournisseurNumeroCNI));
    }

    @GetMapping("/numero-tel/{numero-tel}")
    public ResponseEntity<FournisseurResponse> findFournisseurByNumeroTel(
            @PathVariable("numero-tel") String numeroTel
    ) {
        return ResponseEntity.ok(service.findByNumeroTel(numeroTel));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<FournisseurResponse> findFournisseurByEmail(
            @PathVariable("email") String fournisseurEmail
    ) {
        return ResponseEntity.ok(service.findByEmail(fournisseurEmail));
    }

    @PostMapping(value = "/upload/photo/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFournisseurPhoto(
            @PathVariable("id") Long id,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.savePhoto(file, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFournisseur(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
