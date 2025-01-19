package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.categorie.Categorie;
import com.opmg.ApiGestionStock.categorie.CategorieResponse;
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

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Long save(ClientRequest request) {
        boolean exists = clientRepository.existsByNumeroCNIOrNumeroTelOrEmail(request.numeroCNI(), request.numeroTel(), request.email());
        if (exists) {
            log.error("Client already exist {}", request);
            throw new InvalidEntityException(CLIENT_ALREADY_EXISTS);
        }
        return clientRepository.save(clientMapper.toClient(request)).getId();
    }

    public PageResponse<ClientResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Client> clients = clientRepository.findAll(pageable);
        List<ClientResponse> clientsResponse = clients.stream().map(clientMapper::toClientResponse).toList();
        return new PageResponse<>(
                clientsResponse,
                clients.getNumber(),
                clients.getSize(),
                clients.getTotalElements(),
                clients.isFirst(),
                clients.isLast()
        );
    }

    public ClientResponse findById(Long clientId) {
        return clientRepository.findById(clientId)
                .map(clientMapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByNumeroCNI(String numeroCNI) {
        return clientRepository.findByNumeroCNI(numeroCNI)
                .map(clientMapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByNumeroTel(String numeroTel) {
        return clientRepository.findByNumeroTel(numeroTel)
                .map(clientMapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(clientMapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }
}
