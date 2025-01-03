package com.korit.board_back.controller;


import com.korit.board_back.common.ResponseMessage;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.chat.ChatMessageRequestDto;
import com.korit.board_back.dto.chat.ChatMessageResponseDto;
import com.korit.board_back.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Websocket 메시지 수신 후 Redis로 퍼블리시
    @MessageMapping("/chat/{roomId}")
    public ResponseEntity<ResponseDto<Void>> sendMessage(@DestinationVariable Long roomId, @RequestBody ChatMessageRequestDto chatMessageDto) {
        chatMessageDto.setRoomId(roomId);
        chatService.publishMessage(chatMessageDto);
        return ResponseEntity.ok(ResponseDto.setSuccess(ResponseMessage.SUCCESS, null));
    }

    @GetMapping("/history/{roomId}")
    public ResponseEntity<ResponseDto<List<ChatMessageResponseDto>>> getChatHistory(@PathVariable Long roomId) {
        ResponseDto<List<ChatMessageResponseDto>> response = chatService.getFormattedChatHistory(roomId);

        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}