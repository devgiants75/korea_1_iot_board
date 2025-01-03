import React from 'react'
import { WebSocketProvider } from './ChatContext'
import ChatRoom from './ChatRoom'

export default function index() {
  return (
    <>
      <WebSocketProvider>
      <div>
        <h1>Chat Application</h1>
        <ChatRoom roomId={1} sender="User123" />
      </div>
    </WebSocketProvider>
    </>
  )
}
