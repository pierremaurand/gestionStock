package com.opmg.ApiGestionStock.categorie;

import com.opmg.ApiGestionStock.common.BaseEntity;
import com.opmg.ApiGestionStock.article.Article;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

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

    @OneToMany(mappedBy = "categorie")
    private Collection<Article> articles;

    @Transient
    public List<Long> getArticlesIds(){
        return articles.stream().map(Article::getId).toList();
    }

    @java.beans.Transient
    public Long getTotalArticles(){
        return (long) articles.size();
    }
}
