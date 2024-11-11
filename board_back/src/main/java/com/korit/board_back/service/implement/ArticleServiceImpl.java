package com.korit.board_back.service.implement;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.article.request.ArticleCreateRequestDto;
import com.korit.board_back.dto.article.request.ArticleUpdateRequestDto;
import com.korit.board_back.dto.article.response.ArticleResponseDto;
import com.korit.board_back.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public ResponseDto<ArticleResponseDto> createArticle(Long authorId, ArticleCreateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<ArticleResponseDto> updateArticle(Long authorId, Long id, ArticleUpdateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteArticle(Long authorId, Long id) {
        return null;
    }

    @Override
    public ResponseDto<ArticleResponseDto> getArticle(Long id) {
        return null;
    }
}
