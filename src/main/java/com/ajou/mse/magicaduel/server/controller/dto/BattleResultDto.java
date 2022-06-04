package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.util.BattleResult;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BattleResultDto {

  private BattleResult result;

  @Builder
  public BattleResultDto(BattleResult result) {
    this.result = result;
  }
}
