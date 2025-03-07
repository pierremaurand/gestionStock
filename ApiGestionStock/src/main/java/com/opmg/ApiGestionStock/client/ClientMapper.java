package com.opmg.ApiGestionStock.client;

import com.opmg.ApiGestionStock.common.AdresseMapper;
import com.opmg.ApiGestionStock.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMapper {
    private final AdresseMapper adresseMapper;

    public Client toClient(ClientRequest request) {
        return Client.builder()
                .id(request.id())
                .nom(request.nom())
                .sexe(request.sexe())
                .numeroTel(request.numeroTel())
                .numeroCNI(request.numeroCNI())
                .email(request.email())
                .adresse(adresseMapper.toAdresse(request.adresse()))
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
                .adresse(adresseMapper.toAdresseResponse(client.getAdresse()))
                .value(client.getNom())
                .build();
    }
}
