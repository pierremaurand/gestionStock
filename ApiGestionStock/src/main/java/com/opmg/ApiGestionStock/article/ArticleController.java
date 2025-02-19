package com.opmg.ApiGestionStock.article;

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
@RequestMapping("articles")
@RequiredArgsConstructor
@Tag(name = "Article")
public class ArticleController {
    private final ArticleService service;

    @PostMapping
    public ResponseEntity<Long> saveArticle(@Valid @RequestBody ArticleRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ArticleResponse>> findAllArticles(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ArticleResponse>> findAllArticlesList() {
        return ResponseEntity.ok(service.findAllArticlesList());
    }

    @GetMapping("/filtre/id/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/filtre/code/{code}")
    public ResponseEntity<ArticleResponse> findArticleByCode(
            @PathVariable("code") String code
    ) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @GetMapping("/filtre/categorie/{id}")
    public ResponseEntity<PageResponse<ArticleResponse>> findAllArticlesByCategorieId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllByCategorieId(page, size, id));
    }

    @PostMapping(value = "/upload/photo/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadArticlePhoto(
            @PathVariable("id") Long id,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.savePhoto(file, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
