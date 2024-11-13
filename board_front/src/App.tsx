import React from 'react'
import RootLayout from './components/rootLayout/RootLayout'
import RootContainer from './components/rootContainer/RootContainer'
import Todo from './views/Todo';
import { Route, Routes } from 'react-router-dom';

export default function App() {
  return (
    <RootLayout>
      <RootContainer>
        <Routes>
          <Route path='/' element={ <Todo /> } />
        </Routes>
      </RootContainer>
    </RootLayout>
  )
}
