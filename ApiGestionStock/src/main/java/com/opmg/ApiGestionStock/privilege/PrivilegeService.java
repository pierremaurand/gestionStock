package com.opmg.ApiGestionStock.privilege;

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

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.PRIVILEGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    public Long save(PrivilegeRequest request) {
        boolean exists = privilegeRepository.existsByName(request.name());
        if (exists) {
            log.error("Privilege already exists in the data base");
            throw new InvalidEntityException(BusinessErrorCodes.PRIVILEGE_ALREADY_EXISTS);
        }
        Privilege privilege = privilegeMapper.toPrivilege(request);
        return privilegeRepository.save(privilege).getId();
    }

    public PageResponse<PrivilegeResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Privilege> privileges = privilegeRepository.findAll(pageable);
        List<PrivilegeResponse> privilegesResponse = privileges.stream().map(privilegeMapper::toPrivilegeResponse).toList();
        return new PageResponse<>(
                privilegesResponse,
                privileges.getNumber(),
                privileges.getSize(),
                privileges.getTotalElements(),
                privileges.isFirst(),
                privileges.isLast()
        );
    }

    public PrivilegeResponse findById(Long privilegeId) {
        return privilegeRepository.findById(privilegeId)
                .map(privilegeMapper::toPrivilegeResponse)
                .orElseThrow(() -> new EntityNotFoundException(PRIVILEGE_NOT_FOUND));
    }

    public PrivilegeResponse findByName(String name) {
        return privilegeRepository.findByName(name)
                .map(privilegeMapper::toPrivilegeResponse)
                .orElseThrow(() -> new EntityNotFoundException(PRIVILEGE_NOT_FOUND));
    }
}
