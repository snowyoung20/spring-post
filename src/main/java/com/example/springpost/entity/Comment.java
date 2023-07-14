package com.example.springpost.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String body;

	@Column(name = "likes", nullable = false)
	private int likesCount = 0;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
	private List<Likes> likes = new ArrayList<>();

	public Comment(String body) {
		this.body = body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void increaseLikesCount() {
		this.likesCount++;
	}

	public void decreaseLikesCount() {
		this.likesCount--;
	}
}