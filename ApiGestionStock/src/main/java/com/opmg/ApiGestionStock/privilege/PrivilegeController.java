package com.opmg.ApiGestionStock.privilege;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("privileges")
@Tag(name = "Privilege")
public class PrivilegeController {
    private final PrivilegeService service;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody PrivilegeRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PrivilegeResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/id/{privilege-id}")
    public ResponseEntity<PrivilegeResponse> findById(
            @PathVariable("privilege-id") Long privilegeId
    ) {
        return ResponseEntity.ok(service.findById(privilegeId));
    }

    @GetMapping("/name/{privilege-name}")
    public ResponseEntity<PrivilegeResponse> findByName(
            @PathVariable("privilege-name") String privilegeName
    ) {
        return ResponseEntity.ok(service.findByName(privilegeName));
    }
}
