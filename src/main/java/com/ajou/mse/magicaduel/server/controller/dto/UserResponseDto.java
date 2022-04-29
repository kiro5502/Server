package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private Long id;
  private String nickname;
  private Integer score;
  private Integer win;
  private Integer lose;
  private Integer ranking;

  @Builder
  public UserResponseDto(User entity, int ranking) {
    this.id = entity.getId();
    this.nickname = entity.getNickname();
    this.score = entity.getScore() != null ? entity.getScore() : 0;
    this.win = entity.getWin() != null ? entity.getWin() : 0;
    this.lose = entity.getLose() != null ? entity.getLose() : 0;
    this.ranking = ranking;
  }
}
