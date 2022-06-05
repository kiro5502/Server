package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

  private Long id;
  private String nickname;
  private Integer score;
  private Integer win;
  private Integer lose;
  private Integer draw;
  private Integer ranking;

  @Builder
  public UserDto(User user) {
    this.id = user.getId();
    this.nickname = user.getNickname();
    this.score = user.getScore() != null ? user.getScore() : 0;
    this.win = user.getWin() != null ? user.getWin() : 0;
    this.lose = user.getLose() != null ? user.getLose() : 0;
    this.draw = user.getDraw() != null ? user.getDraw() : 0;
  }
}
