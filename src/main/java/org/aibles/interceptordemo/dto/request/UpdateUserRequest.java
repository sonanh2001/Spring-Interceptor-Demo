package org.aibles.interceptordemo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.aibles.interceptordemo.entity.User;

@Data
public class UpdateUserRequest {

  @NotNull(message = "an updated user must have an id")
  private Long userId;

  @Email
  @NotBlank(message = "email must not blank")
  private String email;

  private String fullName;

  public User toUser(User user) {
    user.setEmail(this.getEmail());
    user.setFullName(this.getFullName());
    return user;
  }
}
