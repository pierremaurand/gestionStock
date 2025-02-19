package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.article.ArticleResponse;
import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(name = "Categorie")
public class CategorieController {
    private final CategorieService service;

    @PostMapping
    public ResponseEntity<Long> saveCategorie(@Valid @RequestBody CategorieRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CategorieResponse>> findAllCategories(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategorieResponse>> findCategoriesList(){
        return ResponseEntity.ok(service.findAllCategoriesList());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategorieResponse> findCategorieById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CategorieResponse> findCategorieById(
            @PathVariable("code") String code
    ) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
