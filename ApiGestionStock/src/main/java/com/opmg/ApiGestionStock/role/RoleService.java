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
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public Long save(RoleRequest request) {
        isRoleExists(request);
        return repository.save(mapper.toRole(request)).getId();
    }

    public PageResponse<RoleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Role> roles = repository.findAll(pageable);
        List<RoleResponse> rolesResponse = roles.stream().map(mapper::toRoleResponse).toList();
        return new PageResponse<>(
                rolesResponse,
                roles.getNumber(),
                roles.getSize(),
                roles.getTotalElements(),
                roles.getTotalPages(),
                roles.isFirst(),
                roles.isLast()
        );
    }

    public RoleResponse findById(Long roleId) {
        return mapper.toRoleResponse(getRoleById(roleId));
    }

    public RoleResponse findByName(String name) {
        return mapper.toRoleResponse(getRoleByName(name));
    }

    public void init() {
        if(repository.findAll().isEmpty()) {
            repository.saveAll(List.of(Role.builder().name("USER").build(), Role.builder().name("ADMIN").build()));
        }
    }

    public Role getRoleById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    public Role getRoleByName(String name){
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    public void isRoleExists(RoleRequest request){
        if (repository.existsByName(request.name()) && request.id() == null) {
            log.error("Role already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.ROLE_ALREADY_EXISTS);
        }
    }

    public List<Role> findAllByIds(List<Long> ids){
        return repository.findAllById(ids);
    }
}
