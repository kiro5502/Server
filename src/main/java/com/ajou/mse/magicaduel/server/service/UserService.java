package com.ajou.mse.magicaduel.server.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignInDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.error.exception.DuplicateException;
import com.ajou.mse.magicaduel.server.error.exception.MismatchException;
import com.ajou.mse.magicaduel.server.error.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final RankingService rankingService;

  private final PasswordEncoder passwordEncoder;
  private final HttpSession httpSession;

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

    User encryptedUser = encrypt(requestDto);
    return new UserResponseDto(userRepository.save(encryptedUser), 0);
  }

  public UserResponseDto signIn(UserSignInDto requestDto) {
    User user = findByEmail(requestDto.getEmail());

    if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
      throw new MismatchException("Password Mismatch");
    }

    int ranking = rankingService.getRanking(user.getId());

    httpSession.setAttribute("user", new SessionUser(user));

    return new UserResponseDto(user, ranking);
  }

  public ResultResponseDto checkDuplicateEmail(String email) {
    return new ResultResponseDto(isDuplicateEmail(email));
  }

  public ResultResponseDto checkDuplicateNickname(String nickname) {
    return new ResultResponseDto(isDuplicateNickname(nickname));
  }

  public boolean isDuplicateEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  public boolean isDuplicateNickname(String nickname) {
    return userRepository.findByNickname(nickname).isPresent();
  }

  public User encrypt(UserSignUpDto requestDto) {
    String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
    User user = User.builder()
        .email(requestDto.getEmail())
        .password(encodedPassword)
        .nickname(requestDto.getNickname())
        .build();

    return user;
  }

  public User findByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException("Not Found Email"));

    return user;
  }

  public User findById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not found user id = " + id));

    return user;
  }
}
