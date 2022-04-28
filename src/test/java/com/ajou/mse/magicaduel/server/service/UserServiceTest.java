package com.ajou.mse.magicaduel.server.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.domain.user.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void 비밀번호_암호화_복호화() {
		// given
		String rawPassword = "1234";

		// when
		String encodedPassword = passwordEncoder.encode(rawPassword);

		// then
		assertNotEquals(rawPassword, encodedPassword);
		assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
	}

	@Test
	void 회원가입_로그인() {
		// given
		String email = "ex@ex.com";
		String password = "1234";
		String nickname = "test";
		UserSignUpDto requestDto = UserSignUpDto.builder()
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();

		// when
		User savedUser = userService.encrypt(requestDto);

		// then
		assertTrue(passwordEncoder.matches(password, savedUser.getPassword()));
	}
}
