/** @jsxImportSource @emotion/react */
import React, { useState } from 'react'
import * as s from './style';
import { useNavigate } from 'react-router-dom';

export default function MyPage() {
  const [principalData, setPrincipalData] = useState<boolean>(false);
  const navigate = useNavigate();

  return (
    <div css={s.layout}>
      <div css={s.header}>
        <div css={s.imgBox}>
          <div css={s.profileImg}>
            <img src="https://cdn.pixabay.com/photo/2020/06/30/22/34/dog-5357794_640.jpg" alt="강아지프로필" />
          </div>
        </div>

        <div css={s.infoBox}>
          <div css={s.infoText}>
            견주 이름: 황현지
          </div>
          <div css={s.infoText}>
            강아지 이름: 쪼꼬미
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

      <div css={s.bottom}>
        하위 영역
      </div>
    </div>
  )
}
