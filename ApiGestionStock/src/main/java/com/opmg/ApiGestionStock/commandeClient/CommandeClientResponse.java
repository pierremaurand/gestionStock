package com.opmg.ApiGestionStock.commandeClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeClientResponse {
    private Long id;
    private String code;
    private LocalDate dateCommande;
    private Long client;
}
