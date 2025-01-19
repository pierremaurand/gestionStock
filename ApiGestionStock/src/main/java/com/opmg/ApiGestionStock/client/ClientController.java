package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@Tag(name = "Client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @PostMapping
    public ResponseEntity<Long> saveClient(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ClientResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{client-id}")
    public ResponseEntity<ClientResponse> findById(
            @PathVariable("client-id") Long clientId
    ) {
        return ResponseEntity.ok(service.findById(clientId));
    }

    @GetMapping("/numero-cni/{client-numero-cni}")
    public ResponseEntity<ClientResponse> findByNumeroCNI(
            @PathVariable("client-numero-cni") String clientNumeroCNI
    ) {
        return ResponseEntity.ok(service.findByNumeroCNI(clientNumeroCNI));
    }

    @GetMapping("/numero-tel/{client-numero-tel}")
    public ResponseEntity<ClientResponse> findByNumeroTel(
            @PathVariable("client-numero-tel") String numeroTel
    ) {
        return ResponseEntity.ok(service.findByNumeroTel(numeroTel));
    }

    @GetMapping("/email/{client-email}")
    public ResponseEntity<ClientResponse> findByEmail(
            @PathVariable("client-email") String clientEmail
    ) {
        return ResponseEntity.ok(service.findByEmail(clientEmail));
    }
}
