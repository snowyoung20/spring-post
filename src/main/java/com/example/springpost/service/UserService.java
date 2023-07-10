package com.example.springpost.service;

import com.example.springpost.dto.AuthRequestDto;
import com.example.springpost.entity.User;
import com.example.springpost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	public void signup(AuthRequestDto requestDto) {
		String username = requestDto.getUsername();
		String password = passwordEncoder.encode(requestDto.getPassword());

		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}

		User user = new User(username, password);
		userRepository.save(user);
	}

	public void login(AuthRequestDto requestDto) {
		String username = requestDto.getUsername();
		String password = requestDto.getPassword();

		//사용자 확인
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new IllegalArgumentException("등록된 사용자가 없습니다.")
		);

		//비밀번호 확인
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
	}
}
