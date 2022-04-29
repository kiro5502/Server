package com.ajou.mse.magicaduel.server.controller.dto;

import java.io.Serializable;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {

  private Long id;

  public SessionUser(User user) {
    this.id = user.getId();
  }
}
