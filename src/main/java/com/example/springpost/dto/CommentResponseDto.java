package com.example.springpost.dto;

import com.example.springpost.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends ApiResponseDto {
	private Long id;
	private String body;
	private String username;
	private int likesCount;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public CommentResponseDto(Comment comment) {
		super();
		this.id = comment.getId();
		this.body = comment.getBody();
		this.username = comment.getUser().getUsername();
		this.likesCount = comment.getLikesCount();
		this.createdAt = comment.getCreatedAt();
		this.modifiedAt = comment.getModifiedAt();
	}
}