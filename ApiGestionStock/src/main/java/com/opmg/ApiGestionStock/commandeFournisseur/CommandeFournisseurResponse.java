package com.opmg.ApiGestionStock.commandeFournisseur;

import com.opmg.ApiGestionStock.fournisseur.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeFournisseurResponse {
    private Long id;
    private String code;
    private LocalDate dateCommande;
    private Long fournisseur;
}
