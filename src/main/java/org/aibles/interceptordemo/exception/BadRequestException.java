package org.aibles.interceptordemo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException{

  public BadRequestException(Object field, Object type) {
    super();
    setCode("org.aibles.interceptordemo.exception.BadRequestException");
    setStatus(HttpStatus.BAD_REQUEST.value());
    addParams("field", field);
    addParams("type", type);
  }
}
