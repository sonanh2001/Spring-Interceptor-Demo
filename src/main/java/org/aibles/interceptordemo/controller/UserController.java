package org.aibles.interceptordemo.controller;

import static org.aibles.interceptordemo.constants.ApiConstants.USERS_API_URI;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.interceptordemo.dto.request.LoginRequest;
import org.aibles.interceptordemo.dto.request.RegisterRequest;
import org.aibles.interceptordemo.dto.request.UpdateUserRequest;
import org.aibles.interceptordemo.dto.response.UserResponse;
import org.aibles.interceptordemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USERS_API_URI)
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService service;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse getById(@PathVariable long id) {
    log.info("(getbyId)id : {}", id);
    return service.getById(id);
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse login(@RequestBody @Valid LoginRequest request) {
    log.info("(login)username : {}", request.getUsername());
    return service.login(request);
  }

  @GetMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  public String logout(@RequestHeader(name = "Authorization") String authorizationHeader) {
    log.info("(logout)header : {}", authorizationHeader);
    service.logout(authorizationHeader);
    return "Logout successfully!!!";
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String register(@RequestBody @Valid RegisterRequest request) {
    log.info("(register)username : {}", request.getUsername());
    service.register(request);
    return "Register successfully!!!";
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse update(@PathVariable long id, @RequestBody @Valid UpdateUserRequest request) {
    log.info("(update)id : {}, fullName : {}", id, request.getFullName());
    return service.update(id, request);
  }
}
