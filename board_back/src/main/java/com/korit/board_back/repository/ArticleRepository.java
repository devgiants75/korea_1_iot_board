package com.korit.board_back.repository;

import com.korit.board_back.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByIdAndAuthorId(Long id, Long authorId);
}
