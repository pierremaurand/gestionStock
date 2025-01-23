package com.opmg.ApiGestionStock.fournisseur;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("fournisseurs")
@Tag(name = "Fournisseur")
@RequiredArgsConstructor
public class FournisseurController {
    private final FournisseurService service;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody FournisseurRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<FournisseurResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FournisseurResponse> findById(
            @PathVariable("id") Long fournisseurId
    ) {
        return ResponseEntity.ok(service.findById(fournisseurId));
    }

    @GetMapping("/numero-cni/{numero-cni}")
    public ResponseEntity<FournisseurResponse> findByNumeroCNI(
            @PathVariable("numero-cni") String fournisseurNumeroCNI
    ) {
        return ResponseEntity.ok(service.findByNumeroCNI(fournisseurNumeroCNI));
    }

    @GetMapping("/numero-tel/{numero-tel}")
    public ResponseEntity<FournisseurResponse> findByNumeroTel(
            @PathVariable("numero-tel") String numeroTel
    ) {
        return ResponseEntity.ok(service.findByNumeroTel(numeroTel));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<FournisseurResponse> findByEmail(
            @PathVariable("email") String fournisseurEmail
    ) {
        return ResponseEntity.ok(service.findByEmail(fournisseurEmail));
    }

    @PostMapping(value = "/upload/photo/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable("id") Long id,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.savePhoto(file, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
