package com.ajou.mse.magicaduel.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationSuccessHandler authenticationSuccessHandler;
  private final AuthenticationFailureHandler authenticationFailureHandler;
  private final UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder encode() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // Disable csrf protection to use basic auth
        .headers().frameOptions().disable() // Disable customizing the XFrameOptionsHeaderWriter
        .and()

        .formLogin()
        .loginPage("/")
        .loginProcessingUrl("user/sign-in")
        .usernameParameter("email")
        .passwordParameter("password")
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler)
        .and()

        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)
        .sessionRegistry(sessionRegistry());
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(encode());
  }
}
