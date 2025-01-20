package com.opmg.ApiGestionStock.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role toRole(RoleRequest request) {
        return Role.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
