package com.opmg.ApiGestionStock.categorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorieResponse {
    private Long id;
    private String code;
    private String designation;
    private byte[] photo;
}
