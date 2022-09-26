package org.aibles.interceptordemo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.interceptordemo.entity.User;

@Data
@NoArgsConstructor
public class RegisterRequest {
  @NotBlank(message = "username must not blank")
  private String username;

  @NotBlank(message = "password must not blank")
  private String password;

  @Email
  @NotBlank(message = "email must not blank")
  private String email;

  public User toUser() {
    User user = new User();
    user.setUsername(this.getUsername());
    user.setEmail(this.getEmail());
    user.setPassword(this.getPassword());
    return user;
  }
}
