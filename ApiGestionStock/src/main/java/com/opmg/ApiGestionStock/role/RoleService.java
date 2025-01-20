package com.opmg.ApiGestionStock.role;

import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public Long save(RoleRequest request) {
        boolean exists = roleRepository.existsByName(request.name());
        if (exists) {
            log.error("Role already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.ROLE_ALREADY_EXISTS);
        }
        return roleRepository.save(roleMapper.toRole(request)).getId();
    }

    public PageResponse<RoleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Role> roles = roleRepository.findAll(pageable);
        List<RoleResponse> rolesResponse = roles.stream().map(roleMapper::toRoleResponse).toList();
        return new PageResponse<>(
                rolesResponse,
                roles.getNumber(),
                roles.getSize(),
                roles.getTotalElements(),
                roles.isFirst(),
                roles.isLast()
        );
    }

    public RoleResponse findById(Long roleId) {
        return roleRepository.findById(roleId)
                .map(roleMapper::toRoleResponse)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    public RoleResponse findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toRoleResponse)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    public void init() {
        if(roleRepository.findAll().isEmpty()) {
            roleRepository.saveAll(List.of(Role.builder().name("USER").build(), Role.builder().name("ADMIN").build()));
        }
    }
}
