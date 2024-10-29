import { Button, Card, CardActions, CardContent, TextField, Typography } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react'
import { useCookies } from 'react-cookie';
import { useNavigate } from 'react-router-dom';

interface Credentials {
  email: string;
  password: string;
}

interface SignInResponseDto {
  token: string;
  user: Credentials;
  exprTime: number;
}

export default function SignIn() {
  const [credentials, setCredentials] = useState<Credentials>({
    email: '',
    password: ''
  });

  const [error, setError] = useState<string>('');

  const [, setCookies] = useCookies(['token']);

  const navigate = useNavigate();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const element = e.target;

    setCredentials({
      ...credentials,
      [element.name]: element.value
    });
  }

  const handleSignIn = async () => {

    const { email, password } = credentials;

    // 이메일 O + 비밀번호 O : false > 실행 X
    // 이메일 X + 비밀번호 O : true > 실행 O
    // 이메일 O + 비밀번호 X : true > 실행 O
    // 이메일 X + 비밀번호 X : true > 실행 O
    if (!email || !password) {
      setError('아이디와 비밀번호를 모두 입력해주세요.');
      return;
    }

    try {
      const response = await axios.post(`http://localhost:8080/api/v1/auth/signIn`, credentials);

      if (response.data) {
        handleSignInSuccess(response.data.data);
      }

    } catch {
      setError('로그인 중 문제가 발생했습니다.');
    }
  };

  // 로그인 성공 시 실행
  // : 서버 응답이 성공일 경우 토큰과 사용자 정보를 저장 & 페이지 이동
  const handleSignInSuccess = (data: SignInResponseDto) => {

  }

  return (
    <Card variant='outlined' sx={{
      width: 360,
      m: 'auto',
      mt: 4
    }}>
      <CardContent>
        <Typography variant='h5' mb={2}>
          로그인
        </Typography>

        {/* 입력 필드 */}
        <TextField
          label="이메일"
          type="email"
          name="email"
          variant="outlined"
          value={credentials.email}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="비밀번호"
          type="password"
          name="password"
          variant="outlined"
          value={credentials.password}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />

        {error && (
          <Typography color='error' mt={2}>
            {error}
          </Typography>
        )}
      </CardContent>

      <CardActions>
        <Button 
          onClick={handleSignIn} 
          fullWidth 
          variant='contained' 
          color='primary'
        >
          로그인
        </Button>
      </CardActions>
    </Card>
  )
}