package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponseDto {

  private Long id;
  private String nickname;
  private Integer score;
  private Integer win;
  private Integer lose;
  private Integer draw;
  private Integer ranking;

  @Builder
  public UserResponseDto(UserDto userDto, int ranking) {
    this.id = userDto.getId();
    this.nickname = userDto.getNickname();
    this.score = userDto.getScore() != null ? userDto.getScore() : 0;
    this.win = userDto.getWin() != null ? userDto.getWin() : 0;
    this.lose = userDto.getLose() != null ? userDto.getLose() : 0;
    this.draw = userDto.getDraw() != null ? userDto.getDraw() : 0;
    this.ranking = ranking;
  }
}
