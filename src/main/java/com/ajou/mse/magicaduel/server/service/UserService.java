package com.ajou.mse.magicaduel.server.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignInDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.domain.battleInfo.BattleInfo;
import com.ajou.mse.magicaduel.server.domain.battleInfo.BattleInfoRepository;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.error.exception.MismatchException;
import com.ajou.mse.magicaduel.server.error.exception.NotFoundException;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BattleInfoRepository battleInfoRepository;

  private final PasswordEncoder passwordEncoder;
  private final HttpSession httpSession;

  @Transactional(rollbackFor = Exception.class)
  public void signUp(UserSignUpDto requestDto) {
    User encryptedUser = encrypt(requestDto);

    BattleInfo battleInfo = battleInfoRepository.save(new BattleInfo());
    encryptedUser.updateBattleInfo(battleInfo);

    userRepository.save(encryptedUser);
  }

  public UserDto signIn(UserSignInDto requestDto) {
    User user = findByEmail(requestDto.getEmail());

    if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
      throw new MismatchException("Password Mismatch");
    }

    httpSession.setAttribute(Consts.SESSION_USER, new SessionUser(user));

    return new UserDto(user);
  }

  public void signOut() {
    httpSession.invalidate();
  }

  public boolean checkDuplicateEmail(String email) {
    return isDuplicateEmail(email);
  }

  public boolean checkDuplicateNickname(String nickname) {
    return isDuplicateNickname(nickname);
  }

  public UserDto getUserInfo(long id) {
    User user = findById(id);
    return new UserDto(user);
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
