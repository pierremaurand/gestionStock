package com.opmg.ApiGestionStock.commandeFournisseur;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opmg.ApiGestionStock.common.EtatCommande;
import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import com.opmg.ApiGestionStock.fournisseur.FournisseurResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandeFournisseurResponse {
    private Long id;
    private String code;
    private LocalDate dateCommande;
    private EtatCommande etatCommande;
    private FournisseurResponse fournisseur;
}
