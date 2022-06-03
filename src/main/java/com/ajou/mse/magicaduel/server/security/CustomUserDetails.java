package com.ajou.mse.magicaduel.server.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
@EqualsAndHashCode(of = { "username" })
public class CustomUserDetails implements UserDetails {

  private Long id;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  @Builder
  public CustomUserDetails(Long id, String email, String password) {
    this.id = id;
    this.username = email;
    this.password = password;
    this.authorities = AuthorityUtils.createAuthorityList("ROLE");
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
