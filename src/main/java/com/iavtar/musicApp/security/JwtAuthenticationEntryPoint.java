package com.iavtar.musicApp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iavtar.musicApp.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Autowired private ObjectMapper objectMapper;

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    ServiceResponse loginExceptionResponse =
        ServiceResponse.builder()
            .message("Invalid Credentials")
            .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
            .build();
    String jsonLoginExceptionResponse = objectMapper.writeValueAsString(loginExceptionResponse);
    response.setContentType("application/json");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().println(jsonLoginExceptionResponse);
  }
}
