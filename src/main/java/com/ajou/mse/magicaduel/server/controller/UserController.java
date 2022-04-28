package com.ajou.mse.magicaduel.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignInDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {

  private final UserService userService;

  @PostMapping("sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto signUp(@RequestBody UserSignUpDto requestDto) {
    return userService.signUp(requestDto);
  }

  @PostMapping("sign-in")
  public UserResponseDto signIn(@RequestBody UserSignInDto requestDto) {
    return userService.signIn(requestDto);
  }

  @GetMapping("check-duplicate-email")
  public ResultResponseDto checkDuplicateEmail(@RequestParam String email) {
    return userService.checkDuplicateEmail(email);
  }

  @GetMapping("check-duplicate-nickname")
  public ResultResponseDto checkDuplicateNickname(@RequestParam String nickname) {
    return userService.checkDuplicateNickname(nickname);
  }
}
