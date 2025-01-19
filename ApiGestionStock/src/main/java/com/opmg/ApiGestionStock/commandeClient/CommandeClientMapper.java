package com.opmg.ApiGestionStock.commandeClient;

import org.springframework.stereotype.Service;

@Service
public class CommandeClientMapper {
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
                .client(commandeClient.getClientId())
                .build();
    }
}
