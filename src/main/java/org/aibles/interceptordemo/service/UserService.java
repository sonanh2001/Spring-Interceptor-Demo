package org.aibles.interceptordemo.service;

import org.aibles.interceptordemo.dto.request.LoginRequest;
import org.aibles.interceptordemo.dto.request.RegisterRequest;
import org.aibles.interceptordemo.dto.request.UpdateUserRequest;
import org.aibles.interceptordemo.dto.response.AuthResponse;
import org.aibles.interceptordemo.dto.response.UserResponse;

public interface UserService {

  /**
   * register new user to system
   * @param request - register request from client
   */
  void register(RegisterRequest request);

  /**
   * login a user to system
   * @param request - login request from client
   * @return - a login response
   */
  UserResponse login(LoginRequest request);

  /**
   * logout a user
   * @param authorizationHeader - header info from client
   */
  void logout(String authorizationHeader);

  /**
   * get a user by id
   * @param id - id from client
   * @return a response of a user
   */
  UserResponse getById(long id);

  /**
   * get a user by username
   * @param username - username of user
   * @return response to authenticate user
   */
  AuthResponse getByUsername(String username);

  /**
   * update a user by id
   * @param id - id from client
   * @param request - request from client
   * @return a response of an updated user
   */
  UserResponse update(long id, UpdateUserRequest request);


}
