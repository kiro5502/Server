package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.battleInfo.BattleInfo;
import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

  private Long id;
  private String nickname;
  private BattleInfo battleInfo;

  @Builder
  public UserDto(User user) {
    this.id = user.getId();
    this.nickname = user.getNickname();
    this.battleInfo = user.getBattleInfo();
  }
}
