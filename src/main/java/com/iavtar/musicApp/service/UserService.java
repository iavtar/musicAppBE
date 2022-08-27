package com.iavtar.musicApp.service;

import com.iavtar.musicApp.model.request.UserSignupRequest;
import com.iavtar.musicApp.model.response.ServiceResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<ServiceResponse> signUpUser(UserSignupRequest request);
}
