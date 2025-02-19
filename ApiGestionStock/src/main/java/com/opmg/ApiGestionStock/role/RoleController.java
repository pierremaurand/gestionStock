package com.opmg.ApiGestionStock.role;

import com.opmg.ApiGestionStock.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
@Tag(name = "Role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @PostMapping
    public ResponseEntity<Long> saveRole(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<RoleResponse>> findAllRoles(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/filtre/id/{role-id}")
    public ResponseEntity<RoleResponse> findRoleById(
            @PathVariable("role-id") Long roleId
    ) {
        return ResponseEntity.ok(service.findById(roleId));
    }

    @GetMapping("/filtre/name/{role-name}")
    public ResponseEntity<RoleResponse> findRoleByName(
            @PathVariable("role-name") String roleName
    ) {
        return ResponseEntity.ok(service.findByName(roleName));
    }
}
