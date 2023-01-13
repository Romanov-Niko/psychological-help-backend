package com.kpi.controller;

import com.kpi.domain.User;
import com.kpi.dto.request.UserRegistrationDto;
import com.kpi.dto.response.UserResponseDto;
import com.kpi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
  private final UserService userService;

  @PostMapping("/")
  public UserResponseDto registration(@RequestBody UserRegistrationDto dto) {
    return convertToUserDto(userService.registration(dto));
  }

  private UserResponseDto convertToUserDto(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole().getName().name())
        .build();
  }
}
