package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.client.Client;
import com.opmg.ApiGestionStock.client.ClientRepository;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
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
public class CommandeClientService {
    private CommandeClientMapper commandeClientMapper;
    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;

    public Long save(CommandeClientRequest request) {
        boolean exists = commandeClientRepository.existsByCode(request.code());
        if (exists) {
            log.error("Cade already use CODE:: {}", request.code());
            throw new InvalidEntityException(CODE_COMMANDE_CLIENT_ALREADY_EXISTS);
        }
        Client client = clientRepository.findById(request.client())
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND));

        CommandeClient commandeClient = commandeClientMapper.toCommandeClient(request);
        commandeClient.setClient(client);
        return commandeClient.getId();
    }

    public PageResponse<CommandeClientResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CommandeClient> commandeClients = commandeClientRepository.findAll(pageable);
        List<CommandeClientResponse> commandeClientResponses = commandeClients.stream().map(commandeClientMapper::toCommandeClientResponse).toList();
        return new PageResponse<>(
                commandeClientResponses,
                commandeClients.getNumber(),
                commandeClients.getSize(),
                commandeClients.getTotalElements(),
                commandeClients.isFirst(),
                commandeClients.isLast()
        );
    }

    public CommandeClientResponse findById(Long id) {
        return commandeClientRepository.findById(id)
                .map(commandeClientMapper::toCommandeClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_CLIENT_NOT_FOUND));
    }

    public CommandeClientResponse findByCode(String code) {
        return commandeClientRepository.findByCode(code)
                .map(commandeClientMapper::toCommandeClientResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_CLIENT_NOT_FOUND));
    }
}
