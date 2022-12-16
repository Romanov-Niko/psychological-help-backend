package com.kpi.controller;

import com.kpi.domain.User;
import com.kpi.dto.request.JwtRequestDto;
import com.kpi.dto.response.JwtResponseDto;
import com.kpi.service.TokenService;
import com.kpi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService userService;
  private final TokenService tokenService;

  @PostMapping("/token")
  public JwtResponseDto generateToken(@RequestBody JwtRequestDto request) {
    User user = userService.getByEmailAndPassword(request.getEmail(), request.getPassword());
    String token = tokenService.generateToken(user);
    return JwtResponseDto.builder().token(token).build();
  }
}
