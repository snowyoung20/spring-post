package com.example.springpost.controller;

import com.example.springpost.dto.ApiResponseDto;
import com.example.springpost.security.UserDetailsImpl;
import com.example.springpost.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

	private final LikesService likesService;

	@PostMapping("/post/{id}")
	public ResponseEntity<ApiResponseDto> likesPost(@PathVariable Long id,
	                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
		ApiResponseDto result = likesService.likesPost(id, userDetails);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/comment/{id}")
	public ResponseEntity<ApiResponseDto> LikesComment(@PathVariable Long id,
	                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
		ApiResponseDto result = likesService.likesComment(id, userDetails);
		return ResponseEntity.ok().body(result);
	}
}
