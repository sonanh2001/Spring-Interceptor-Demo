package org.aibles.interceptordemo.service.impl;

import static org.aibles.interceptordemo.constants.CommonConstants.TOKEN_INDEX;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.interceptordemo.dto.request.LoginRequest;
import org.aibles.interceptordemo.dto.request.RegisterRequest;
import org.aibles.interceptordemo.dto.request.UpdateUserRequest;
import org.aibles.interceptordemo.dto.response.AuthResponse;
import org.aibles.interceptordemo.dto.response.UserResponse;
import org.aibles.interceptordemo.entity.User;
import org.aibles.interceptordemo.exception.BadRequestException;
import org.aibles.interceptordemo.exception.NotFoundException;
import org.aibles.interceptordemo.exception.UsernameExistedException;
import org.aibles.interceptordemo.repository.UserRepository;
import org.aibles.interceptordemo.service.UserService;
import org.aibles.interceptordemo.util.BasicAuthUtil;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private static final String TYPE_OF_OBJECT = "user";
  private final UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public UserResponse getById(long id) {
    log.info("(getById)id : {}", id);
    User user =
        repository.findById(id).orElseThrow(() -> new NotFoundException(id, TYPE_OF_OBJECT));
    return UserResponse.from(user);
  }

  @Override
  @Transactional
  public UserResponse login(LoginRequest request) {
    log.info("(login)username : {}", request.getUsername());
    if (!repository.existsByUsernameAndPassword(request.getUsername(), request.getPassword())) {
      throw new BadRequestException(request.getUsername(), TYPE_OF_OBJECT);
    }
    User user =
        repository
            .findByUsername(request.getUsername())
            .orElseThrow(() -> new NotFoundException(request.getUsername(), TYPE_OF_OBJECT));
    user.setEnabled(true);
    return UserResponse.from(repository.save(user));
  }

  @Override
  @Transactional
  public void logout(String authorizationHeader) {
    log.info("(logout)header : {}", authorizationHeader);
    String token = authorizationHeader.substring(TOKEN_INDEX);
    User user =
        repository
            .findByUsername(BasicAuthUtil.getInstance().getUsernameFromToken(token))
            .orElseThrow(
                () ->
                    new NotFoundException(
                        BasicAuthUtil.getInstance().getUsernameFromToken(token), TYPE_OF_OBJECT));
    user.setEnabled(false);
    repository.save(user);
  }

  @Override
  @Transactional
  public void register(RegisterRequest request) {
    log.info("(register)username : {}", request.getUsername());
    if (repository.existsByUsername(request.getUsername())) {
      throw new UsernameExistedException(request.getUsername());
    }
    User user = request.toUser();
    user.setEnabled(false);
    repository.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public AuthResponse getByUsername(String username) {
    log.info("(getByUsername)username : {}", username);
    User user =
        repository
            .findByUsername(username)
            .orElseThrow(() -> new NotFoundException(username, TYPE_OF_OBJECT));
    return AuthResponse.from(user);
  }

  @Override
  @Transactional
  public UserResponse update(long id, UpdateUserRequest request) {
    log.info("(update)id : {},email : {}", id, request.getEmail());
    User user =
        repository
            .findById(id)
            .map(request::toUser)
            .orElseThrow(() -> new NotFoundException(id, TYPE_OF_OBJECT));
    return UserResponse.from(repository.save(user));
  }
}
