package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

  private Long id;
  private String email;
  private String password;
  private String nickname;
  private Integer score;
  private Integer win;
  private Integer lose;

  @Builder
  public UserResponseDto(User entity) {
    this.id = entity.getId();
    this.email = entity.getEmail();
    this.password = entity.getPassword();
    this.nickname = entity.getNickname();
    this.score = entity.getScore() != null ? entity.getScore() : 0;
    this.win = entity.getWin() != null ? entity.getWin() : 0;
    this.lose = entity.getLose() != null ? entity.getLose() : 0;
  }
}
