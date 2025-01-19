package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.commandeClient.CommandeClient;
import com.opmg.ApiGestionStock.commandeClient.CommandeClientResponse;
import com.opmg.ApiGestionStock.common.PageResponse;
import com.opmg.ApiGestionStock.exception.EntityNotFoundException;
import com.opmg.ApiGestionStock.exception.InvalidEntityException;
import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import com.opmg.ApiGestionStock.fournisseur.FournisseurRepository;
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
public class CommandeFournisseurService {
    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final CommandeFournisseurMapper commandeFournisseurMapper;

    public Long save(CommandeFournisseurRequest request) {
        boolean exists = commandeFournisseurRepository.existsByCode(request.code());
        if (exists) {
            log.error("Code commande fournisseur already exists CODE::{}", request.code());
            throw new InvalidEntityException(CODE_COMMANDE_FOURNISSEUR_ALREADY_EXISTS);
        }
        Fournisseur fournisseur = fournisseurRepository.findById(request.fournisseur())
                .orElseThrow(() -> new EntityNotFoundException(FOURNISSEUR_NOT_FOUND));

        CommandeFournisseur commandeFournisseur = commandeFournisseurMapper.toCommandeFournisseur(request);
        commandeFournisseur.setFournisseur(fournisseur);
        return commandeFournisseurRepository.save(commandeFournisseur).getId();
    }

    public PageResponse<CommandeFournisseurResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAll(pageable);
        List<CommandeFournisseurResponse> commandeFournisseurResponses = commandeFournisseurs.stream().map(commandeFournisseurMapper::toCommandeFournisseurResponse).toList();
        return new PageResponse<>(
                commandeFournisseurResponses,
                commandeFournisseurs.getNumber(),
                commandeFournisseurs.getSize(),
                commandeFournisseurs.getTotalElements(),
                commandeFournisseurs.isFirst(),
                commandeFournisseurs.isLast()
        );
    }

    public CommandeFournisseurResponse findById(Long id) {
        return commandeFournisseurRepository.findById(id)
                .map(commandeFournisseurMapper::toCommandeFournisseurResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    public CommandeFournisseurResponse findByCode(String code) {
        return commandeFournisseurRepository.findByCode(code)
                .map(commandeFournisseurMapper::toCommandeFournisseurResponse)
                .orElseThrow(() -> new EntityNotFoundException(COMMANDE_FOURNISSEUR_NOT_FOUND));
    }
}
