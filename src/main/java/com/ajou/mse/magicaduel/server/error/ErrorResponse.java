package com.ajou.mse.magicaduel.server.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

  private String code;
  private String message;

}
