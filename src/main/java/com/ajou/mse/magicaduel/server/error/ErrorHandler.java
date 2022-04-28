package com.ajou.mse.magicaduel.server.error;

import com.ajou.mse.magicaduel.server.error.exception.DuplicateException;
import com.ajou.mse.magicaduel.server.error.exception.MismatchException;
import com.ajou.mse.magicaduel.server.error.exception.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 @RestController에 적용되는 클래스
public class ErrorHandler {

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(DuplicateException.class)
  public ErrorResponse handleDuplicateException(DuplicateException e) {
    return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MismatchException.class)
  public ErrorResponse handleMismatchException(MismatchException e) {
    return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ErrorResponse handleNotFoundException(NotFoundException e) {
    return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage());
  }
}
