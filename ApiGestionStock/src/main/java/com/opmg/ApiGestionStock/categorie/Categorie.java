package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.article.Article;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie extends BaseEntity {
    @Column(unique = true)
    private String code;
    private String designation;
    private String photo;

    @OneToMany(mappedBy = "categorie")
    private Collection<Article> articles;
}
