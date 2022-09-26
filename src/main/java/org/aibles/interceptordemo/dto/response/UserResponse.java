package org.aibles.interceptordemo.dto.response;

import lombok.Data;
import org.aibles.interceptordemo.entity.User;

@Data
public class UserResponse {
  private Long userId;

  private String username;

  private String fullName;

  private String email;

  public static UserResponse from(User user) {
    UserResponse response = new UserResponse();
    response.setUserId(user.getUserId());
    response.setUsername(user.getUsername());
    response.setFullName(user.getFullName());
    response.setEmail(user.getEmail());
    return response;
  }
}
