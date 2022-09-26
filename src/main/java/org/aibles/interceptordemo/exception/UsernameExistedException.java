package org.aibles.interceptordemo.exception;

import org.springframework.http.HttpStatus;

public class UsernameExistedException extends BaseException {

  public UsernameExistedException(String username) {
    super();
    setCode("org.aibles.interceptordemo.exception.UsernameExistedException");
    setStatus(HttpStatus.BAD_REQUEST.value());
    addParams("username", username);
  }
}
