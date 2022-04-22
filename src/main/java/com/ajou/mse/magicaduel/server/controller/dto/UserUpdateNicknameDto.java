package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateNicknameDto {

  private Long id;
  private String nickname;

  @Builder
  public UserUpdateNicknameDto(Long id, String nickname) {
    this.id = id;
    this.nickname = nickname;
  }
}
