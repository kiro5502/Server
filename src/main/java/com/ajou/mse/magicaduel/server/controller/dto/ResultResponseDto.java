package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResultResponseDto {

  private boolean result;

  @Builder
  public ResultResponseDto(boolean result) {
    this.result = result;
  }
}
