package com.ajou.mse.magicaduel.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.annotation.CheckLogin;
import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignInDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.error.exception.DuplicateException;
import com.ajou.mse.magicaduel.server.service.RankingService;
import com.ajou.mse.magicaduel.server.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자를 통한 의존성 주입
@RestController
@RequestMapping("/user/")
public class UserController {

  private final UserService userService;
  private final RankingService rankingService;

  @PostMapping("sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public ResultResponseDto signUp(@RequestBody UserSignUpDto requestDto) {
    // id 중복체크
    if (userService.isDuplicateEmail(requestDto.getEmail())) {
      throw new DuplicateException("Duplicated Email");
    }

    // nickname 중복체크
    if (userService.isDuplicateNickname(requestDto.getNickname())) {
      throw new DuplicateException("Duplicated Nickname");
    }

    userService.signUp(requestDto);

    return new ResultResponseDto(true);
  }

  @PostMapping("sign-in")
  public UserResponseDto signIn(@RequestBody UserSignInDto requestDto) {
    UserDto userDto = userService.signIn(requestDto);
    int ranking = rankingService.getRanking(userDto.getId());
    return new UserResponseDto(userDto, ranking);
  }

  @PostMapping("sign-out")
  @CheckLogin
  public ResultResponseDto signOut() {
    userService.signOut();

    return new ResultResponseDto(true);
  }

  @GetMapping("duplicate-email")
  public ResultResponseDto checkDuplicateEmail(@RequestParam String email) {
    return new ResultResponseDto(userService.checkDuplicateEmail(email));
  }

  @GetMapping("duplicate-nickname")
  public ResultResponseDto checkDuplicateNickname(@RequestParam String nickname) {
    return new ResultResponseDto(userService.checkDuplicateNickname(nickname));
  }

  @GetMapping("{id}")
  public UserResponseDto info(@PathVariable long id) {
    UserDto userDto = userService.getUserInfo(id);
    int ranking = rankingService.getRanking(userDto.getId());
    return new UserResponseDto(userDto, ranking);
  }
}
