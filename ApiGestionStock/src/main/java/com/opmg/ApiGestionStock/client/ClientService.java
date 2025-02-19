package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.AdresseMapper;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.file.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.opmg.ApiGestionStock.handler.BusinessErrorCodes.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final AdresseMapper adresseMapper;
    private final FileStorageService fileStorageService;

    public Long save(ClientRequest request) {
        boolean exists = repository.existsByNumeroCNIOrNumeroTelOrEmail(request.numeroCNI(), request.numeroTel(), request.email());
        if (exists && request.id() == null) {
            log.error("Client already exist {}", request);
            throw new InvalidEntityException(CLIENT_ALREADY_EXISTS);
        }

        Client client = mapper.toClient(request);
        if(request.id() != null) {
            client = getClientById(request.id());
            client.setNom(request.nom());
            client.setNumeroCNI(request.numeroCNI());
            client.setSexe(request.sexe());
            client.setNumeroTel(request.numeroTel());
            client.setEmail(request.email());
            client.setAdresse(adresseMapper.toAdresse(request.adresse()));
        }
        return repository.save(client).getId();
    }

    public PageResponse<ClientResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Client> clients = repository.findAll(pageable);
        List<ClientResponse> clientsResponse = clients.stream().map(mapper::toClientResponse).toList();
        return new PageResponse<>(
                clientsResponse,
                clients.getNumber(),
                clients.getSize(),
                clients.getTotalElements(),
                clients.getTotalPages(),
                clients.isFirst(),
                clients.isLast()
        );
    }

    public List<ClientResponse> findAllClientsList() {
        return repository.findAll().stream()
                .map(mapper::toClientResponse)
                .toList();
    }

    public ClientResponse findById(Long clientId) {
        return repository.findById(clientId)
                .map(mapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByNumeroCNI(String numeroCNI) {
        return repository.findByNumeroCNI(numeroCNI)
                .map(mapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByNumeroTel(String numeroTel) {
        return repository.findByNumeroTel(numeroTel)
                .map(mapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public ClientResponse findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }

    public void delete(Long id){
        Client client = getClientById(id);
        if(client.getCommandeClients().isEmpty()){
            repository.deleteById(id);
        }
    }

    public void savePhoto(MultipartFile file, Long id) {
        Client client = getClientById(id);
        var photo = fileStorageService.saveFile(file, "client", String.valueOf(id));
        client.setPhoto(photo);
        repository.save(client);
    }

    public Client getClientById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));
    }
}
