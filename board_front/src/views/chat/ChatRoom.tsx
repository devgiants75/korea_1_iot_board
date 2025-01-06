import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useWebSocket } from './ChatContext';
import { StompSubscription } from '@stomp/stompjs';

interface Message {
  id: number;
  roomId: number;
  sender: string;
  message: string;
  timestamp: string;
}

const ChatRoom: React.FC<{ roomId: number; sender: string }> = ({ roomId, sender }) => {
  const { sendMessage, subscribe, connected } = useWebSocket();
  const [messages, setMessages] = useState<Message[]>([]);
  const [newMessage, setNewMessage] = useState<string>('');
  const subscriptionRef = React.useRef<StompSubscription | null>(null);

  useEffect(() => {
    const fetchHistoryAndSubscribe = async () => {
      console.log('object');
      try {
        // 채팅 히스토리 가져오기
        const response = await axios.get(`http://localhost:4040/api/v1/chat/history/${roomId}`);
        console.log(response.data.data);
        setMessages(response.data.data);

        // WebSocket 구독
        if (connected) {
          const subscription = subscribe(`/topic/${roomId}`, (message) => {
            console.log(message);
            const body: Message = JSON.parse(message.body);
            setMessages((prev) => [...prev, body]);
          });

          subscriptionRef.current = subscription;
        }
      } catch (error) {
        console.error('Error setting up WebSocket:', error);
      }
    };

    fetchHistoryAndSubscribe();

    return () => {
      // 구독 해제
      subscriptionRef.current?.unsubscribe();
      subscriptionRef.current = null;
    };
  }, [roomId, connected, subscribe]);

  const handleSendMessage = () => {
    if (!newMessage.trim()) return;

    const messagePayload = {
      sender,
      message: newMessage,
    };

    sendMessage(`/app/chat/${roomId}`, messagePayload); // WebSocket으로 메시지 전송
    setNewMessage('');
  };

  return (
    <div>
      <h2>채팅방: {roomId}</h2>
      <div style={{ maxHeight: '400px', overflowY: 'auto', border: '1px solid #ddd', padding: '10px' }}>
        {messages.map((msg) => (
          <div key={msg.id}>
            <strong>{msg.sender}:</strong> {msg.message} <em>({new Date(msg.timestamp).toLocaleTimeString()})</em>
          </div>
        ))}
      </div>
      <div>
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="메시지를 입력하세요..."
          style={{ width: '80%', padding: '5px' }}
        />
        <button onClick={handleSendMessage} style={{ width: '18%', marginLeft: '2%' }}>
          전송
        </button>
      </div>
    </div>
  );
};

export default ChatRoom;
