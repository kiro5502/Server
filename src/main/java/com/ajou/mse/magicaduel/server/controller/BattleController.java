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
import com.ajou.mse.magicaduel.server.controller.dto.UserDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.service.BattleService;
import com.ajou.mse.magicaduel.server.service.RankingService;
import com.ajou.mse.magicaduel.server.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/battle/")
public class BattleController {

  private final BattleService battleService;
  private final UserService userService;
  private final RankingService rankingService;

  @PostMapping("start")
  @CheckLogin
  public ResultResponseDto start(@LoginUser SessionUser sessionUser) {
    User user = userService.findById(sessionUser.getId());

    battleService.setPrevScore(user.getScore());

    UserDto userDto = userService.lose(user);
    rankingService.setScore(userDto.getId(), userDto.getScore());

    return new ResultResponseDto(true);
  }

  @PostMapping("result")
  @CheckLogin
  public UserResponseDto result(@RequestBody BattleResultDto requestDto, @LoginUser SessionUser sessionUser) {
    User user = userService.findById(sessionUser.getId());
    int prevScore = battleService.getAndRemovePrevScore();

    userService.cancelLose(user, prevScore);

    UserDto userDto = null;

    switch (requestDto.getResult()) {
      case WIN:
        userDto = userService.win(user);
        break;

      case LOSE:
        userDto = userService.lose(user);
        break;

      case DRAW:
        userDto = userService.draw(user);
        break;
    }

    rankingService.setScore(userDto.getId(), userDto.getScore());

    int ranking = rankingService.getRanking(userDto.getId());

    return new UserResponseDto(userDto, ranking);
  }
}
