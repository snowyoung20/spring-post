package com.example.springpost.repository;

import com.example.springpost.entity.Comment;
import com.example.springpost.entity.Likes;
import com.example.springpost.entity.Post;
import com.example.springpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
	Optional<Likes> findByUserAndPost(User user, Post post);
	Optional<Likes> findByUserAndComment(User user, Comment comment);
}
