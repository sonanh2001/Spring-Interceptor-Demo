package org.aibles.interceptordemo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException{

  public NotFoundException(Object field, Object type) {
    super();
    setStatus(HttpStatus.NOT_FOUND.value());
    setCode("org.aibles.interceptordemo.exception.NotFoundException");
    addParams("field", field);
    addParams("type", type);
  }
}
