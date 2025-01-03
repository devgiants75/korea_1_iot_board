import React from 'react'
import RootLayout from './components/rootLayout/RootLayout'
import RootContainer from './components/rootContainer/RootContainer'
import Todo from './views/Todo';
import { Route, Routes } from 'react-router-dom';
import MyPage from './views/myPage/MyPage';
import Chart from './views/Chart/Chart';
import ChatMain from './views/chat/index';

export default function App() {
  return (
    <RootLayout>
      <RootContainer>
        <Routes>
          <Route path='/todo' element={ <Todo /> } />
          <Route path='/' element={ <MyPage /> } />
          <Route path='/chart' element={ <Chart /> } />
          <Route path='/chat' element={ <ChatMain /> } />
        </Routes>
      </RootContainer>
    </RootLayout>
  )
}
