package com.korit.board_back.service.implement;

import com.korit.board_back.common.ResponseMessage;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.chat.ChatMessageRequestDto;
import com.korit.board_back.dto.chat.ChatMessageResponseDto;
import com.korit.board_back.entity.ChatMessage;
import com.korit.board_back.repository.ChatRepository;
import com.korit.board_back.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService  {

    private final ChatRepository chatRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseDto<Void> saveMessage(ChatMessageRequestDto dto) {
        try {
            ChatMessage message = new ChatMessage();
            message.setRoomId(dto.getRoomId());
            message.setSender(dto.getSender());
            message.setMessage(dto.getMessage());
            message.setTimestamp(LocalDateTime.now());
            chatRepository.save(message);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<Void> publishMessage(ChatMessageRequestDto dto) {
        try {
            saveMessage(dto);
            redisTemplate.convertAndSend("chatroom:" + dto.getRoomId(), dto);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("REDIS ERROR");
        }
    }

    @Override
    public ResponseDto<List<ChatMessageResponseDto>> getFormattedChatHistory(Long roomId) {
        List<ChatMessageResponseDto> data = null;

        try {
            List<ChatMessage> history = chatRepository.findByRoomIdOrderByTimestampAsc(roomId);
            data = history.stream()
                    .map(msg -> new ChatMessageResponseDto(
                            msg.getId(),
                            msg.getRoomId(),
                            msg.getSender(),
                            msg.getMessage(),
                            msg.getTimestamp()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
