package com.korit.board_back.controller;

import com.korit.board_back.common.ApiMappingPattern;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.auth.request.LoginRequestDto;
import com.korit.board_back.dto.auth.request.NaverLoginRequestDto;
import com.korit.board_back.dto.auth.request.SignUpRequestDto;
import com.korit.board_back.dto.auth.response.LoginResponseDto;
import com.korit.board_back.dto.auth.response.SignUpResponseDto;
import com.korit.board_back.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private static final String SIGN_UP_PATH = "/signUp";
    private static final String LOGIN_PATH = "/login";
    private static final String OAUTH_NAVER_LOGIN = "/login/naver";

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        ResponseDto<LoginResponseDto> response = authService.login(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(OAUTH_NAVER_LOGIN)
    public ResponseEntity<ResponseDto> naverLogin(@RequestBody NaverLoginRequestDto requestDto) {
        String jwt = authService.loginWithNaver(requestDto.getToken());
        return ResponseEntity.ok(ResponseDto.setSuccess("로그인 성공", jwt));
    }
}
