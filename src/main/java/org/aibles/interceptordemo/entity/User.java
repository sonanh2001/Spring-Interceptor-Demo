package org.aibles.interceptordemo.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(unique = true, length = 50, nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  @Column(length = 100)
  private String fullName;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private Boolean enabled;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return userId != null && Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
