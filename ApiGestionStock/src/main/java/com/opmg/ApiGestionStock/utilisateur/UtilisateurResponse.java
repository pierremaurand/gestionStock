package com.opmg.ApiGestionStock.utilisateur;

import com.opmg.ApiGestionStock.common.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Sexe sexe;
    private byte[] photo;
    private List<Long> roles;
}
