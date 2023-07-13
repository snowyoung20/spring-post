package com.example.springpost.controller;

import java.util.concurrent.RejectedExecutionException;

import com.example.springpost.dto.ApiResponseDto;
import com.example.springpost.dto.PostListResponseDto;
import com.example.springpost.dto.PostRequestDto;
import com.example.springpost.dto.PostResponseDto;
import com.example.springpost.security.UserDetailsImpl;
import com.example.springpost.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
		PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());

		return ResponseEntity.status(201).body(result);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostListResponseDto> getPosts() {
		PostListResponseDto result = postService.getPosts();

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
		PostResponseDto result = postService.getPostById(id);

		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<ApiResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
		try {
			PostResponseDto result = postService.updatePost(id, requestDto, userDetails.getUser());
			return ResponseEntity.ok().body(result);
		} catch (RejectedExecutionException e) {
			return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
		}
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
		try {
			postService.deletePost(id, userDetails.getUser());
			return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
		} catch (RejectedExecutionException e) {
			return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
		}
	}
}