package com.ajou.mse.magicaduel.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserUpdateNicknameDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.error.exception.DuplicateException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  @Transactional(rollbackFor = Exception.class)
  public UserResponseDto signUp(UserSignUpDto requestDto) {
    // id 중복체크
    if (isDuplicateEmail(requestDto.getEmail())) {
      throw new DuplicateException("Duplicated Email");
    }

    // nickname 중복체크
    if (isDuplicateNickname(requestDto.getNickname())) {
      throw new DuplicateException("Duplicated Nickname");
    }

    return new UserResponseDto(userRepository.save(requestDto.toEntity()));
  }

  public boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  public boolean isDuplicateNickname(String nickname) {
    return userRepository.findByNickname(nickname).isPresent();
  }

  @Transactional(rollbackFor = Exception.class)
  public UserResponseDto updateNickname(UserUpdateNicknameDto requestDto) {
    User user = userRepository.findById(requestDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Not found user id = " + requestDto.getId()));

    user.update(requestDto.getNickname());
    return new UserResponseDto(user);
  }

  public UserResponseDto findById(Long id) {
    User entity = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not found user id = " + id));

    return new UserResponseDto(entity);
  }
}
