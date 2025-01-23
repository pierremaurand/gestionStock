package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Utilisateur")
public class UtilisateurController {
    private final UtilisateurService service;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody UtilisateurRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<UtilisateurResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UtilisateurResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<UtilisateurResponse> findByLogin(@PathVariable("login") String login){
        return ResponseEntity.ok(service.findByLogin(login));
    }

    @PatchMapping("/mot-de-passe/update")
    public ResponseEntity<Long> changePassword(@Valid @RequestBody ChangePasswordRequest request){
        return ResponseEntity.ok(service.changePassword(request));
    }

    @PostMapping(value = "/photo/upload/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable("id") Long id,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.savePhoto(file, id);
        return ResponseEntity.accepted().build();
    }
}
