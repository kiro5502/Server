package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInDto {

  private String email;
  private String password;

  @Builder
  public UserSignInDto(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
