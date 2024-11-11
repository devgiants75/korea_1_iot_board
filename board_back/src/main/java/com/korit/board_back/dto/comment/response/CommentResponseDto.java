package com.korit.board_back.dto.comment.response;

import com.korit.board_back.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commenterId;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.commenterId = comment.getCommenterId();
        this.content = comment.getContent();
    }
}
