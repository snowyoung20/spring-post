package com.example.springpost.controller;

import com.example.springpost.dto.ApiResponseDto;
import com.example.springpost.dto.AuthRequestDto;
import com.example.springpost.jwt.JwtUtil;
import com.example.springpost.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponseDto> signUp(@Valid @RequestBody AuthRequestDto requestDto) {

		userService.signup(requestDto);

		return ResponseEntity.status(201).body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
	}

	//로그인
	@PostMapping("/login")
	public ResponseEntity<ApiResponseDto> login(@RequestBody AuthRequestDto loginRequestDto, HttpServletResponse response) {
		userService.login(loginRequestDto);

		//JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
		response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getUsername()));

		return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
	}
}
