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

    @JoinColumn(name = "article_id", nullable = false)

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @Column(nullable = false, name = "commenter_id")
    private Long commenterId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
