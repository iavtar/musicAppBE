package com.iavtar.musicApp.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String fullName;

  private String email;

  private String countryCode;

  private String mobileNumber;

  private String password;

  private String otp;

  @Enumerated(EnumType.STRING)
  private UserAuthState authState;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Date updatedAt;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "USER_ROLE",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private Set<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (!this.roles.isEmpty()) {
      return (Collection<? extends GrantedAuthority>)
          this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().getName()));
    }
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    if (this.authState.equals(UserAuthState.ACTIVE)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    if (this.authState.equals(UserAuthState.ACTIVE)) {
      return true;
    }
    return false;
  }
}
