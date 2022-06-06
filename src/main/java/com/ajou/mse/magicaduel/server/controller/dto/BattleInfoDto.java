package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.battleinfo.BattleInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BattleInfoDto {

  private Long id;
  private String nickname;
  private Integer score;
  private Integer win;
  private Integer lose;
  private Integer draw;
  private Integer ranking;

  @Builder
  public BattleInfoDto(BattleInfo battleInfo) {
    this.score = battleInfo.getScore() != null ? battleInfo.getScore() : 0;
    this.win = battleInfo.getWin() != null ? battleInfo.getWin() : 0;
    this.lose = battleInfo.getLose() != null ? battleInfo.getLose() : 0;
    this.draw = battleInfo.getDraw() != null ? battleInfo.getDraw() : 0;
  }
}
