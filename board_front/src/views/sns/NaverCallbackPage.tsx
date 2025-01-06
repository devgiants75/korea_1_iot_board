import React, { useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';

const NaverCallbackPage = () => {
  const navigate = useNavigate();
  const [cookies, setCookie] = useCookies(['token']);

  useEffect(() => {
    const hash = window.location.hash;
    const token = new URLSearchParams(hash.substring(1)).get('access_token');

    if (token) {
      axios.post('http://localhost:4040/api/v1/auth/login/naver', { token })
        .then((response) => {
          setCookie('token', response.data.token, {
            path: '/',
            secure: true, // HTTPS에서만 쿠키 사용 (옵션)
            sameSite: 'strict', // CSRF 방지 (옵션)
            maxAge: 86400, // 쿠키 유효 시간: 1일 (초 단위)
          });
          navigate('/');
        })
        .catch((error) => console.error('Error during login:', error));
    }
  }, [navigate, setCookie]);

  return <div>로그인 처리 중...</div>;
};

export default NaverCallbackPage;
