package com.ajou.mse.magicaduel.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignInDto;
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

  public UserResponseDto signIn(UserSignInDto requestDto) {
    User user = findByEmail(requestDto.getEmail());
    return new UserResponseDto(user);
  }

  public ResultResponseDto checkDuplicateEmail(String email) {
    return new ResultResponseDto(isDuplicateEmail(email));
  }

  public ResultResponseDto checkDuplicateNickname(String nickname) {
    return new ResultResponseDto(isDuplicateNickname(nickname));
  }

  private boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  private boolean isDuplicateNickname(String nickname) {
    return userRepository.findByNickname(nickname).isPresent();
  }

  @Transactional(rollbackFor = Exception.class)
  public UserResponseDto updateNickname(UserUpdateNicknameDto requestDto) {
    User user = findById(requestDto.getId());
    user.update(requestDto.getNickname());

    return new UserResponseDto(user);
  }

  private User findByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Not found user email = " + email));

    return user;
  }

  private User findById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not found user id = " + id));

    return user;
  }
}
