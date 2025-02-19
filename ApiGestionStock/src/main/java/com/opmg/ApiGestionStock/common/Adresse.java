package com.opmg.ApiGestionStock.common;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class Adresse {
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;
}
