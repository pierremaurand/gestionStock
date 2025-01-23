package com.opmg.ApiGestionStock.commandeClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.client.ClientResponse;
import com.opmg.ApiGestionStock.common.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandeClientResponse {
    private Long id;
    private String code;
    private LocalDate dateCommande;
    private EtatCommande etatCommande;
    private ClientResponse client;
}
