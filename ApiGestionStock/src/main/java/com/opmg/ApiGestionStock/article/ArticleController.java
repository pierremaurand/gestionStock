package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
@Tag(name = "Article")
public class ArticleController {
    private final ArticleService service;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody ArticleRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ArticleResponse>> findAllCategories(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{article-id}")
    public ResponseEntity<ArticleResponse> findById(
            @PathVariable("article-id") Long articleId
    ) {
        return ResponseEntity.ok(service.findById(articleId));
    }

    @GetMapping("/code/{article-code}")
    public ResponseEntity<ArticleResponse> findById(
            @PathVariable("article-code") String articleCode
    ) {
        return ResponseEntity.ok(service.findByCode(articleCode));
    }

    @PostMapping(value = "/photo/{article-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadCategoriePicture(
            @PathVariable("article-id") Long articleId,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.uploadPicture(file, articleId);
        return ResponseEntity.accepted().build();
    }
}
