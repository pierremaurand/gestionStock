package com.opmg.ApiGestionStock.commandeClient;

import com.opmg.ApiGestionStock.client.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeClientMapper {
    private final ClientMapper clientMapper;

    public CommandeClient toCommandeClient(CommandeClientRequest request) {
        return CommandeClient.builder()
                .id(request.id())
                .code(request.code())
                .dateCommande(request.dateCommande())
                .build();
    }

    public CommandeClientResponse toCommandeClientResponse(CommandeClient commandeClient) {
        return CommandeClientResponse.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(clientMapper.toClientResponse(commandeClient.getClient()))
                .build();
    }
}
