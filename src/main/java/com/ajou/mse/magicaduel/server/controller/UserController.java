package com.ajou.mse.magicaduel.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserUpdateNicknameDto;
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

  @PostMapping("update-nickname")
  public UserResponseDto updateNickname(@RequestBody UserUpdateNicknameDto requestDto) {
    return userService.updateNickname(requestDto);
  }

  @GetMapping("{id}")
  public UserResponseDto findById(@PathVariable Long id) {
    return userService.findById(id);
  }
}
