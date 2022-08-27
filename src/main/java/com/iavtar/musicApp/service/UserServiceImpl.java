package com.iavtar.musicApp.service;

import com.iavtar.musicApp.entity.Role;
import com.iavtar.musicApp.entity.User;
import com.iavtar.musicApp.entity.UserAuthState;
import com.iavtar.musicApp.entity.UserRoleType;
import com.iavtar.musicApp.model.request.UserSignupRequest;
import com.iavtar.musicApp.model.response.ServiceResponse;
import com.iavtar.musicApp.repository.RoleRepository;
import com.iavtar.musicApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired RoleRepository roleRepository;

  @Override
  public ResponseEntity<ServiceResponse> signUpUser(UserSignupRequest request) {
    try {
      Optional<User> existedUser = userRepository.findByUsername(request.getUsername());
      if (existedUser.isPresent()) {
        if (existedUser.get().getUsername().equals(request.getUsername())) {
          return new ResponseEntity<>(
              ServiceResponse.builder()
                  .message("username already registered")
                  .code(HttpStatus.CONFLICT.value())
                  .build(),
              HttpStatus.CONFLICT);
        } else if (existedUser.get().getEmail().equals(request.getEmail())) {
          return new ResponseEntity<>(
              ServiceResponse.builder()
                  .message("email already registered")
                  .code(HttpStatus.CONFLICT.value())
                  .build(),
              HttpStatus.CONFLICT);
        } else if (existedUser.get().getCountryCode().equals(existedUser.get().getCountryCode())
            && existedUser.get().getMobileNumber().equals(request.getMobileNumber())) {
          return new ResponseEntity<>(
              ServiceResponse.builder()
                  .message("mobile already registered")
                  .code(HttpStatus.CONFLICT.value())
                  .build(),
              HttpStatus.CONFLICT);
        }
      } else {
        Optional<Role> role = roleRepository.findByName(UserRoleType.USER.name());
        if (role.isEmpty()) {
          Role newRole = Role.builder().name(UserRoleType.USER).build();
          roleRepository.save(newRole);
        }
        userRepository.save(
            User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .countryCode(request.getCountryCode())
                .mobileNumber(request.getMobileNumber())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .authState(UserAuthState.ACTIVE)
                .build());
      }
      return new ResponseEntity<>(
          ServiceResponse.builder()
              .message("signup successful")
              .code(HttpStatus.OK.value())
              .build(),
          HttpStatus.OK);
    } catch (Exception exception) {
      throw new RuntimeException(exception.getMessage());
    }
  }
}
