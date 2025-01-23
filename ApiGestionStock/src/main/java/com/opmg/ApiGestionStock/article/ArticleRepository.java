package com.opmg.ApiGestionStock.article;

import com.opmg.ApiGestionStock.categorie.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByCode(String code);
    boolean existsByCode(String code);
    Page<Article> findAllByIdIn(List<Long> articlesIds, Pageable pageable);
}
