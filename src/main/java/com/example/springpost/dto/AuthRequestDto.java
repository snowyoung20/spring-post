package com.example.springpost.dto;

import com.example.springpost.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
	@Pattern(regexp = "^[a-z0-9]{4,10}$")
	private String username;

	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{8,15}$")
	private String password;

	private UserRoleEnum role; // 회원 권한 (ADMIN, USER)
}
