package org.aibles.interceptordemo.configuration;

import lombok.RequiredArgsConstructor;
import org.aibles.interceptordemo.interceptor.UserInterceptor;
import org.aibles.interceptordemo.repository.UserRepository;
import org.aibles.interceptordemo.service.UserService;
import org.aibles.interceptordemo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.interceptordemo.repository"})
@ComponentScan(basePackages = {"org.aibles.interceptordemo.repository"})
@RequiredArgsConstructor
public class UserConfiguration implements WebMvcConfigurer {

  private final UserRepository userRepository;

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(userInterceptor(userService(userRepository)));
  }

  @Bean
  public UserInterceptor userInterceptor(UserService userService) {
    return new UserInterceptor(userService);
  }

  @Bean
  public UserService userService(UserRepository repository) {
    return new UserServiceImpl(repository);
  }
}
