package com.example.springpost.service;

import com.example.springpost.dto.ApiResponseDto;
import com.example.springpost.entity.Comment;
import com.example.springpost.entity.Likes;
import com.example.springpost.entity.Post;
import com.example.springpost.entity.User;
import com.example.springpost.jwt.JwtUtil;
import com.example.springpost.repository.CommentRepository;
import com.example.springpost.repository.LikesRepository;
import com.example.springpost.repository.PostRepository;
import com.example.springpost.repository.UserRepository;
import com.example.springpost.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;
	private final LikesRepository likesRepository;
	private final JwtUtil jwtUtil;

	@Transactional
	public ApiResponseDto likesPost(Long id, UserDetailsImpl userDetails) {
		User user = userDetails.getUser();
		Post post = postRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
		);

		// 해당 사용자가 게시글에 좋아요를 한적이 있는지 체크
		Optional<Likes> checkUserAndPost = likesRepository.findByUserAndPost(user, post);

		// 좋아요를 했던 상태라면 좋아요를 취소하도록
		if (checkUserAndPost.isPresent()) {
			likesRepository.delete(checkUserAndPost.get());
			post.decreaseLikesCount();
			return new ApiResponseDto("좋아요가 취소되었습니다.", 200);
		} else { // 좋아요를 한적이 없다면
			likesRepository.save(new Likes(user, post));
			post.increaseLikesCount();
			return new ApiResponseDto("해당 게시글을 좋아요 합니다.", 200);
		}
	}

	@Transactional
	public ApiResponseDto likesComment(Long id, UserDetailsImpl userDetails) {
		User user = userDetails.getUser();
		Comment comment = commentRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
		);

		// 해당 사용자가 댓글에 좋아요를 한적이 있는지 체크
		Optional<Likes> checkUserAndComment = likesRepository.findByUserAndComment(user, comment);

		// 좋아요를 했던 상태라면 좋아요를 취소하도록
		if (checkUserAndComment.isPresent()) {
			likesRepository.delete(checkUserAndComment.get());
			comment.decreaseLikesCount();
			return new ApiResponseDto("좋아요가 취소되었습니다.", 200);
		} else { // 좋아요를 한적이 없다면
			likesRepository.save(new Likes(user, comment));
			comment.increaseLikesCount();
			return new ApiResponseDto("해당 댓글을 좋아요 합니다.", 200);
		}
	}
}
