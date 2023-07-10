package com.example.springpost.dto;

import com.example.springpost.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private String username;

	public PostResponseDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.createdAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
		this.username = post.getUser().getUsername();
	}
}
