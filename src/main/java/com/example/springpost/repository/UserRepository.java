package com.example.springpost.repository;

import java.util.Optional;

import com.example.springpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}