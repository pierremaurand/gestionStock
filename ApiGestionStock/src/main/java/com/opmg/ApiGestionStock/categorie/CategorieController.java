package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(name = "Categorie")
public class CategorieController {
    private final CategorieService service;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody CategorieRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CategorieResponse>> findAllCategories(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{categorie-id}")
    public ResponseEntity<CategorieResponse> findById(
            @PathVariable("categorie-id") Long categorieId
    ) {
        return ResponseEntity.ok(service.findById(categorieId));
    }

    @GetMapping("/code/{categorie-code}")
    public ResponseEntity<CategorieResponse> findById(
            @PathVariable("categorie-code") String categorieCode
    ) {
        return ResponseEntity.ok(service.findByCode(categorieCode));
    }

    @PostMapping(value = "/photo/{categorie-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadCategoriePicture(
            @PathVariable("categorie-id") Long categorieId,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.uploadPicture(file, categorieId);
        return ResponseEntity.accepted().build();
    }
}
