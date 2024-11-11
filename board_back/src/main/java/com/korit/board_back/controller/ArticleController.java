package com.korit.board_back.controller;

import com.korit.board_back.common.ApiMappingPattern;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.article.request.ArticleCreateRequestDto;
import com.korit.board_back.dto.article.request.ArticleUpdateRequestDto;
import com.korit.board_back.dto.article.response.ArticleResponseDto;
import com.korit.board_back.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.ARTICLE)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private static final String ARTICLE_GET = "/{id}";
    private static final String ARTICLE_PUT = "/{id}";
    private static final String ARTICLE_DELETE = "/{id}";

    @PostMapping
    public ResponseEntity<ResponseDto<ArticleResponseDto>> createArticle(
            @AuthenticationPrincipal String userId,
            @RequestBody ArticleCreateRequestDto dto
    ) {
        // 사용자 ID(Ex. devgiants75)를 PK값(Bigint)로 저장하기 위한 형 변환
        Long authorId = Long.parseLong(userId);
        ResponseDto<ArticleResponseDto> response = articleService.createArticle(authorId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(ARTICLE_PUT)
    public ResponseEntity<ResponseDto<ArticleResponseDto>> updateArticle(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequestDto dto
    ) {
        Long authorId = Long.parseLong(userId);
        ResponseDto<ArticleResponseDto> response = articleService.updateArticle(authorId, id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(ARTICLE_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteArticle(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id
    ) {
        Long authorId = Long.parseLong(userId);
        ResponseDto<Void> response = articleService.deleteArticle(authorId, id);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(ARTICLE_GET)
    public ResponseEntity<ResponseDto<ArticleResponseDto>> getArticle(@PathVariable Long id) {
        ResponseDto<ArticleResponseDto> response = articleService.getArticle(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
