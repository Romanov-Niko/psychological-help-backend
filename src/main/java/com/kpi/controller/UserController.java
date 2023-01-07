package com.kpi.controller;

import com.kpi.domain.User;
import com.kpi.dto.response.UserResponseDto;
import com.kpi.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping("/{id}")
  public UserResponseDto getById(@PathVariable Integer id) {
    User user = service.getById(id);
    return convertToDto(user);
  }

  @GetMapping("/")
  public List<UserResponseDto> getAll() {
    List<User> users = service.getAll();
    return users.stream().map(this::convertToDto).toList();
  }

  private UserResponseDto convertToDto(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole().getName().name())
        .build();
  }
}
