package com.ajou.mse.magicaduel.server.controller.dto;

import java.io.Serializable;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SessionUser implements Serializable {

  private Long id;
  private String nickname;

  public SessionUser(User user) {
    this.id = user.getId();
    this.nickname = user.getNickname();
  }
}
