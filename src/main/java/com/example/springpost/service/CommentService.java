package com.example.springpost.service;

import com.example.springpost.dto.CommentRequestDto;
import com.example.springpost.dto.CommentResponseDto;
import com.example.springpost.entity.Comment;
import com.example.springpost.entity.Post;
import com.example.springpost.entity.User;
import com.example.springpost.entity.UserRoleEnum;
import com.example.springpost.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final PostService postService;
	private final CommentRepository commentRepository;

	public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
		Post post = postService.findPost(requestDto.getPostId());
		Comment comment = new Comment(requestDto.getBody());
		comment.setUser(user);
		comment.setPost(post);

		var savedComment = commentRepository.save(comment);

		return new CommentResponseDto(savedComment);
	}

	public void deleteComment(Long id, User user) {
		Comment comment = commentRepository.findById(id).orElseThrow();

		// 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
		if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
			throw new RejectedExecutionException();
		}

		commentRepository.delete(comment);
	}

	@Transactional
	public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
		Comment comment = commentRepository.findById(id).orElseThrow();

		// 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
		if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
			throw new RejectedExecutionException();
		}

		comment.setBody(requestDto.getBody());

		return new CommentResponseDto(comment);
	}

}