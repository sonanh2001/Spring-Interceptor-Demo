package org.aibles.interceptordemo.interceptor;

import static org.aibles.interceptordemo.constants.ApiConstants.LOGIN_URI;
import static org.aibles.interceptordemo.constants.ApiConstants.USERS_API_URI;
import static org.aibles.interceptordemo.constants.CommonConstants.START_OF_HEADER;
import static org.aibles.interceptordemo.constants.CommonConstants.TOKEN_INDEX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.interceptordemo.dto.response.AuthResponse;
import org.aibles.interceptordemo.exception.UnauthorizedException;
import org.aibles.interceptordemo.service.UserService;
import org.aibles.interceptordemo.util.BasicAuthUtil;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
  private static final String POST_METHOD = "POST";
  private final UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info(
        "(preHandle)header : {}, path : {}",
        request.getHeader(AUTHORIZATION),
        request.getServletPath());
    if (request.getMethod().equals(POST_METHOD)
        && request.getRequestURI().equals(LOGIN_URI)) {
      return true;
    }

    if (request.getMethod().equals(POST_METHOD) && request.getRequestURI().equals(USERS_API_URI)) {
      return true;
    }

    String authorizationHeader = request.getHeader(AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith(START_OF_HEADER)) {
      log.info("(preHandle)header : {}", authorizationHeader);
      String token = authorizationHeader.substring(TOKEN_INDEX);
      String username = BasicAuthUtil.getInstance().getUsernameFromToken(token);
      String password = BasicAuthUtil.getInstance().getPasswordFromToken(token);

      AuthResponse authResponse = userService.getByUsername(username);
      if (authResponse.getPassword().equals(password) && authResponse.getEnabled()) {
        return true;
      } else {
        throw new UnauthorizedException();
      }
    } else {
      throw new UnauthorizedException();
    }
  }
}
