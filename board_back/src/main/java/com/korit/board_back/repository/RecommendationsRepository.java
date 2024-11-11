package com.korit.board_back.repository;

import com.korit.board_back.entity.Recommendation;
import com.korit.board_back.entity.RecommendationsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationsRepository extends JpaRepository<Recommendation, RecommendationsId> {
}
