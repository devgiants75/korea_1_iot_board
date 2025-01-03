import axios from 'axios';
import React, { useEffect } from 'react'
import { useSearchParams } from 'react-router-dom';

export default function VerifyEmail() {
  const [searchParams] = useSearchParams();
  const token = searchParams.get("token"); // URL의 token 값 추출

  useEffect(() => {
    if (token) {
      verifyEmail(token);
    }
  }, [token]);

  const verifyEmail = async (token: string) => {
    try {
      const response = await axios.get(`http://localhost:4040/api/v1/mail/verify`, {
        params: { token }, 
      });
      console.log(response.data);
      alert(`인증 성공: ${response.data}`);
    } catch (error) {
      console.error(error);
      alert("인증 중 오류가 발생했습니다.");
    }
  };

  return (
    <div>VerifyEmail</div>
  )
}
