package org.aibles.interceptordemo.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException{

  public UnauthorizedException() {
    super();
    setCode("org.aibles.interceptordemo.exception.UnauthorizedException");
    setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
