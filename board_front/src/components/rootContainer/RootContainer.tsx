/** @jsxImportSource @emotion/react */
import React, { ReactNode } from 'react'
import * as s from './style';

interface RootContainerProps {
  children: ReactNode;
}

export default function RootContainer({ children }: RootContainerProps) {
  return (
    <div css={s.container}>
      {children}
    </div>
  )
}
