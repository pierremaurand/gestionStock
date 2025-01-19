package com.opmg.ApiGestionStock.client;

import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public Client toClient(ClientRequest request) {
        return Client.builder()
                .id(request.id())
                .nom(request.nom())
                .prenom(request.prenom())
                .sexe(request.sexe())
                .numeroTel(request.numeroTel())
                .numeroCNI(request.numeroCNI())
                .email(request.email())
                .build();
    }

    public ClientResponse toClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .sexe(client.getSexe())
                .numeroCNI(client.getNumeroCNI())
                .numeroTel(client.getNumeroTel())
                .email(client.getEmail())
                .build();
    }
}
