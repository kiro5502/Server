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
    this.score = userDto.getBattleInfo().getScore() != null ? userDto.getBattleInfo().getScore() : 0;
    this.win = userDto.getBattleInfo().getWin() != null ? userDto.getBattleInfo().getWin() : 0;
    this.lose = userDto.getBattleInfo().getLose() != null ? userDto.getBattleInfo().getLose() : 0;
    this.draw = userDto.getBattleInfo().getDraw() != null ? userDto.getBattleInfo().getDraw() : 0;
    this.ranking = ranking;
  }

  @Builder
  public UserResponseDto(UserDto userDto, BattleInfoDto battleInfoDto, int ranking) {
    this.id = userDto.getId();
    this.nickname = userDto.getNickname();
    this.score = battleInfoDto.getScore() != null ? battleInfoDto.getScore() : 0;
    this.win = battleInfoDto.getWin() != null ? battleInfoDto.getWin() : 0;
    this.lose = battleInfoDto.getLose() != null ? battleInfoDto.getLose() : 0;
    this.draw = battleInfoDto.getDraw() != null ? battleInfoDto.getDraw() : 0;
    this.ranking = ranking;
  }
}
