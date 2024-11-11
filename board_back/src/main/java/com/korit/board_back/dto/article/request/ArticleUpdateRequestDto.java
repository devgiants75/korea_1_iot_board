package com.korit.board_back.dto.article.request;

import lombok.Getter;

@Getter
public class ArticleUpdateRequestDto {
    private String title;
    private String content;
}
