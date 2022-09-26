package org.aibles.interceptordemo.repository;

import java.util.Optional;
import org.aibles.interceptordemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByUsernameAndPassword(String username, String password);
}
