package com.opmg.ApiGestionStock.client;

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
@RequestMapping("clients")
@Tag(name = "Client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @PostMapping
    public ResponseEntity<Long> saveClient(@Validated @RequestBody ClientRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ClientResponse>> findAllClients(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientResponse>> findAllClientsList() {
        return ResponseEntity.ok(service.findAllClientsList());
    }

    @GetMapping("/id/{client-id}")
    public ResponseEntity<ClientResponse> findClientById(
            @PathVariable("client-id") Long clientId
    ) {
        return ResponseEntity.ok(service.findById(clientId));
    }

    @GetMapping("/numero-cni/{client-numero-cni}")
    public ResponseEntity<ClientResponse> findClientByNumeroCNI(
            @PathVariable("client-numero-cni") String clientNumeroCNI
    ) {
        return ResponseEntity.ok(service.findByNumeroCNI(clientNumeroCNI));
    }

    @GetMapping("/numero-tel/{client-numero-tel}")
    public ResponseEntity<ClientResponse> findClientByNumeroTel(
            @PathVariable("client-numero-tel") String numeroTel
    ) {
        return ResponseEntity.ok(service.findByNumeroTel(numeroTel));
    }

    @GetMapping("/email/{client-email}")
    public ResponseEntity<ClientResponse> findClientByEmail(
            @PathVariable("client-email") String clientEmail
    ) {
        return ResponseEntity.ok(service.findByEmail(clientEmail));
    }

    @PostMapping(value = "/upload/photo/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadClientPhoto(
            @PathVariable("id") Long id,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.savePhoto(file, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.accepted().build();
    }
}
