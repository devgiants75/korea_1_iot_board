import React, { createContext, useContext, useEffect, useRef, useState } from 'react';
import { Client, IMessage, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

type WebSocketContextType = {
  sendMessage: (destination: string, body: any) => void;
  subscribe: (destination: string, callback: (message: IMessage) => void) => StompSubscription | null;
  connected: boolean;
};

const WebSocketContext = createContext<WebSocketContextType | null>(null);

export const WebSocketProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const stompClient = useRef<Client | null>(null);
  const [connected, setConnected] = useState(false); 

  useEffect(() => {
    const socket = new SockJS('http://localhost:4040/ws');
    stompClient.current = new Client({
      webSocketFactory: () => socket as WebSocket,
      debug: (msg) => console.log(msg),
      onConnect: () => {
        console.log('Connected to WebSocket');
        setConnected(true);
      },
      onDisconnect: () => {
        console.log('Disconnected from WebSocket');
        setConnected(false);
      },
    });

    stompClient.current.activate();

    return () => {
      stompClient.current?.deactivate();
    };
  }, []);

  const sendMessage = (destination: string, body: any) => {
    if (stompClient.current && connected) {
      stompClient.current.publish({ destination, body: JSON.stringify(body) });
    } else {
      console.error('WebSocket is not connected.');
    }
  };

  const subscribe = (destination: string, callback: (message: IMessage) => void): StompSubscription | null => {
    if (stompClient.current && connected) {
      return stompClient.current.subscribe(destination, callback);
    }
    console.error('WebSocket is not connected.');
    return null;
  };

  return (
    <WebSocketContext.Provider value={{ sendMessage, subscribe, connected }}>
      {children}
    </WebSocketContext.Provider>
  );
};

export const useWebSocket = () => {
  const context = useContext(WebSocketContext);
  if (!context) {
    throw new Error('useWebSocket must be used within a WebSocketProvider');
  }
  return context;
};