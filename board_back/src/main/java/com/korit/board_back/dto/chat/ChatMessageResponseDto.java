package com.korit.board_back.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageResponseDto {
    private Long id;
    private Long roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
}
