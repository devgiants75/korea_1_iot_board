import React, { useState } from 'react'
import useAuthStore from '../../stores/auth.store';

export default function Header() {
  //* state *//
  const [isLogged, setIsLogged] = useState<boolean>(true);

  const { isAuthenticated, user, logout } = useAuthStore();

  return (
    <div>
      {user && (
        <>
          <button>
            {isAuthenticated ? '로그아웃' : '로그인'}
          </button>
          <p>{user.name}</p>
        </>
      )}
    </div>
  )
}
