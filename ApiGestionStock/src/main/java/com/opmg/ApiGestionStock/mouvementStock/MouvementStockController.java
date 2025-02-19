package com.opmg.ApiGestionStock.mouvementStock;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mouvements-stock")
@Tag(name = "Mouvement Stock")
public class MouvementStockController {
    private final MouvementStockService service;

    @PostMapping
    public ResponseEntity<Long> saveMouvementStock(@Valid @RequestBody MouvementStockRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<MouvementStockResponse>> findAllMouvementStocks(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MouvementStockResponse> findMouvementStockById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findMouvementStockById(id));
    }

}
