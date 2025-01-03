package com.korit.board_back.redis;

import com.korit.board_back.dto.chat.ChatMessageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public RedisSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleMessage(ChatMessageRequestDto message) {
        System.out.println("topic 연결" + message);
        messagingTemplate.convertAndSend("/topic/" + message.getRoomId(), message);
    }
}
