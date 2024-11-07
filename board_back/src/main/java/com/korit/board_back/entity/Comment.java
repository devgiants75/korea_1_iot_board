package com.korit.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "article_id")
    private Long articleId;

    @Column(nullable = false, name = "commenter_id")
    private Long commenterId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
