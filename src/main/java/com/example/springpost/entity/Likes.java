package com.example.springpost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "likes")
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

	public Likes(User user, Post post) {
		setUser(user);
		setPost(post);
	}

	public Likes(User user, Comment comment) {
		setUser(user);
		setComment(comment);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;

		if (!post.getLikes().contains(this))
			post.getLikes().add(this);
	}

	public void setComment(Comment comment) {
		this.comment = comment;

		if (!comment.getLikes().contains(this))
			comment.getLikes().add(this);
	}
}
