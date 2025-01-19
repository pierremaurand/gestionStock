package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByCode(String code);

    boolean existsByCode(String code);
}
