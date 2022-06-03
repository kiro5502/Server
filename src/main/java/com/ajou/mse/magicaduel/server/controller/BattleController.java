package com.ajou.mse.magicaduel.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.annotation.CheckLogin;
import com.ajou.mse.magicaduel.server.annotation.LoginUser;
import com.ajou.mse.magicaduel.server.controller.dto.BattleResultDto;
import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.service.BattleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/battle/")
public class BattleController {

  private final BattleService battleService;

  @PostMapping("start")
  @CheckLogin
  public ResultResponseDto start(@LoginUser SessionUser sessionUser) {
    return battleService.start(sessionUser);
  }

  @PostMapping("result")
  @CheckLogin
  public UserResponseDto result(@RequestBody BattleResultDto requestDto, @LoginUser SessionUser sessionUser) {
    return battleService.result(requestDto, sessionUser);
  }
}
