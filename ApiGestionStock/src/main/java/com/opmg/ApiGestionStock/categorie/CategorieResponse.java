package com.opmg.ApiGestionStock.categorie;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieResponse {
    private Long id;
    private String code;
    private String designation;
    private String value;
    private Long totalArticles;
}
