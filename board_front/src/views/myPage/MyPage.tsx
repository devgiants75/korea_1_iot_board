/** @jsxImportSource @emotion/react */
import React, { useEffect, useState } from 'react';
import * as s from './style';
import { useNavigate } from 'react-router-dom';

import Drug from '../drug'

export default function MyPage() {
  const [principalData, setPrincipalData] = useState<boolean>(false);
  const navigate = useNavigate();
  
  const [base64, setBase64] = useState<string | null>();
  const [imageData, setImageData] = useState<string | null>();


  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    console.log(file);
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
        setBase64(reader.result as string);
      }
    }
  }

  const handleSubmit = () => {
    if (base64) {
      try {
        
      } catch (error) {
        console.error("파일 업로드 실패: ", error);
      }
    }
  }

  useEffect(() => {
    setImageData('이미지 담기');
  }, [])

  return (
    <div css={s.layout}>
      <div css={s.header}>
        <div css={s.imgBox}>
          <div css={s.profileImg}>
            <input 
              type="file" 
              onChange={handleFileChange}
            />
            {base64 
            && 
            <img 
              src={base64}
              alt='profile'
              style={{ maxWidth: '200px' }}
              
              // "https://cdn.pixabay.com/photo/2020/06/30/22/34/dog-5357794_640.jpg" 
            />}
            
            <button onClick={handleSubmit}>uplaod</button>
          </div>
        </div>

        <div css={s.infoBox}>
          <div css={s.infoText}>
            견주 이름: 황현지
          </div>
          <div css={s.infoText}>
            강아지 이름: 쪼꼬미
          </div>
          <div>
          {base64 
            && 
            <img 
              src={base64}
              alt='profile'
              style={{ maxWidth: '200px' }}
              
              // "https://cdn.pixabay.com/photo/2020/06/30/22/34/dog-5357794_640.jpg" 
            />}
          </div>
          <div css={s.emailBox}>
            <div css={s.infoText}>
              이메일: jjokkomi@example.com
            </div>
            {principalData ? (
              <div css={s.emailCheck}>✔</div>
            ) : (
              <button css={s.infoButton}>인증</button>
            )}
          </div>

          {/* const navigate = useNavigate(); */}
          <div css={s.infoButtons}>
            <button css={s.infoButton}>정보 수정</button>
            <button css={s.infoButton} onClick={() => navigate('/todo')}>
              비밀번호 수정
            </button>
          </div>
        </div>
      </div>

      <Drug />

      <div css={s.bottom}>
        {
          imageData 
          ? <img src={imageData} alt='저장된 이미지' /> 
          : '이미지 불러오는 중'
        }
      </div>
      
    </div>
  )
}
