package com.korit.board_back.dto.chat;

import lombok.Data;

@Data
public class ChatMessageRequestDto {
    private Long roomId;
    private String sender;
    private String message;
}
