package org.aibles.interceptordemo.dto.response;

import lombok.Data;
import org.aibles.interceptordemo.entity.User;

@Data
public class AuthResponse {
  private String username;

  private String password;

  private Boolean enabled;

  public static AuthResponse from(User user) {
    AuthResponse response = new AuthResponse();
    response.setUsername(user.getUsername());
    response.setPassword(user.getPassword());
    response.setEnabled(user.getEnabled());
    return response;
  }
}
