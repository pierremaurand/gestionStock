package com.opmg.ApiGestionStock.privilege;

import org.springframework.stereotype.Service;

@Service
public class PrivilegeMapper {
    public Privilege toPrivilege(PrivilegeRequest request) {
        return Privilege.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }

    public PrivilegeResponse toPrivilegeResponse(Privilege privilege) {
        return PrivilegeResponse.builder()
                .id(privilege.getId())
                .name(privilege.getName())
                .build();
    }
}
