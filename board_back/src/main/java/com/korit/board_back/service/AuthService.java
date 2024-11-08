package com.korit.board_back.service;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.auth.request.LoginRequestDto;
import com.korit.board_back.dto.auth.request.SignUpRequestDto;
import com.korit.board_back.dto.auth.response.LoginResponseDto;
import com.korit.board_back.dto.auth.response.SignUpResponseDto;

public interface AuthService {

    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseDto<LoginResponseDto> login(LoginRequestDto dto);
}
