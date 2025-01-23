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
    public ResponseEntity<PageResponse<ArticleResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/filtre/id/{id}")
    public ResponseEntity<ArticleResponse> findById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/filtre/code/{code}")
    public ResponseEntity<ArticleResponse> findByCode(
            @PathVariable("code") String code
    ) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @GetMapping("/filtre/categorie/{id}")
    public ResponseEntity<PageResponse<ArticleResponse>> findByCategorieId(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllByCategorieId(page, size, id));
    }

    @PostMapping(value = "/upload/photo/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPicture(
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
