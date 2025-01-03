package com.korit.board_back.service;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.chat.ChatMessageRequestDto;
import com.korit.board_back.dto.chat.ChatMessageResponseDto;

import java.util.List;

public interface ChatService {
    ResponseDto<Void> saveMessage(ChatMessageRequestDto dto);
    ResponseDto<Void> publishMessage(ChatMessageRequestDto dto);

    ResponseDto<List<ChatMessageResponseDto>> getFormattedChatHistory(Long roomId);
}
