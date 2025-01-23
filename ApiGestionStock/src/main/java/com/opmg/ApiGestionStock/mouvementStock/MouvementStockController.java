package com.opmg.ApiGestionStock.mouvementStock;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("mouvements-stock")
@Tag(name = "Mouvement Stock")
public class MouvementStockController {
    private final MouvementStockService service;

    @GetMapping("/article/stock-reel/{id}")
    public ResponseEntity<Double> getStockReelByArticle(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.stockReelByArticle(id));
    }

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody MouvementStockRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<MouvementStockResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<PageResponse<MouvementStockResponse>> findAllByArticle(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(service.findAllByArticle(page, size, id));
    }
}
