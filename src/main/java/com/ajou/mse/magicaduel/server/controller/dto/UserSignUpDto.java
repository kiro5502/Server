package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserSignUpDto {

  private String email;
  private String password;
  private String nickname;

  @Builder
  public UserSignUpDto(String email, String password, String nickname) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public User toEntity() {
    return User.builder()
        .email(email)
        .password(password)
        .nickname(nickname)
        .build();
  }
}
