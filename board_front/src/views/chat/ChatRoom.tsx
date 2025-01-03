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
    const setupWebSocket = async () => {
      try {
        console.log('chatRoom start');
        if (!connected) {
          console.error('WebSocket is not connected.');
          return;
        }
        console.log('chatRoom connected');

        // 채팅 히스토리 가져오기
        const response = await axios.get(`http://localhost:4040/api/v1/chat/history/${roomId}`);
        setMessages(response.data.data);

        // WebSocket 구독
        const subscription = subscribe(`/topic/${roomId}`, (message) => {
          const body: Message = JSON.parse(message.body);
          setMessages((prev) => [...prev, body]);
        });

        if (subscription) {
          subscriptionRef.current = subscription;
        }
      } catch (error) {
        console.error('Error setting up WebSocket:', error);
      }
    };

    setupWebSocket();

    return () => {
      if (subscriptionRef.current) {
        subscriptionRef.current.unsubscribe();
        subscriptionRef.current = null;
        console.log('Unsubscribed from WebSocket topic:', `/topic/${roomId}`);
      }
    };
  }, [roomId, connected]);

  const handleSendMessage = async () => {
    if (!newMessage.trim()) {
      console.error('Message cannot be empty.');
      return;
    }

    const messagePayload = {
      sender,
      message: newMessage,
    };
    sendMessage(`/app/chat/${roomId}`, messagePayload);
    setNewMessage('');
  };

  return (
    <div>
      <h2>Chat Room: {roomId}</h2>
      <div style={{ maxHeight: '400px', overflowY: 'auto', border: '1px solid #ddd', padding: '10px' }}>
        {messages.map((msg) => (
          <div key={msg.id}>
            <strong>{msg.sender}:</strong> {msg.message} <em>({new Date(msg.timestamp).toLocaleTimeString()})</em>
          </div>
        ))}
      </div>
      <div style={{ marginTop: '10px' }}>
        <input
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="Type your message..."
          style={{ width: '80%', padding: '5px' }}
        />
        <button onClick={handleSendMessage} style={{ width: '18%', marginLeft: '2%' }}>
          Send
        </button>
      </div>
    </div>
  );
};

export default ChatRoom;
