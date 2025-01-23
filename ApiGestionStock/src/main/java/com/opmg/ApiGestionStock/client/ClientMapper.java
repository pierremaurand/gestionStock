package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public Client toClient(ClientRequest request) {
        return Client.builder()
                .nom(request.nom())
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
                .sexe(client.getSexe())
                .numeroCNI(client.getNumeroCNI())
                .numeroTel(client.getNumeroTel())
                .email(client.getEmail())
                .photo(FileUtils.readFileFromLocation(client.getPhoto()))
                .build();
    }
}
