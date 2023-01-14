package com.kpi.controller;

import com.kpi.domain.User;
import com.kpi.dto.request.UserRequestDto;
import com.kpi.dto.response.UserResponseDto;
import com.kpi.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public UserResponseDto getById(@PathVariable Integer id) {
    User user = userService.getById(id);
    return convertToUserDto(user);
  }

  @GetMapping("/")
  public List<UserResponseDto> getAll() {
    List<User> users = userService.getAll();
    return users.stream().map(this::convertToUserDto).toList();
  }

  @PostMapping("/")
  public UserResponseDto save(@RequestBody UserRequestDto dto) {
    return convertToUserDto(userService.save(dto));
  }

  @PutMapping("/{id}")
  public UserResponseDto update(@RequestBody UserRequestDto dto, @PathVariable Integer id) {
    return convertToUserDto(userService.update(dto, id));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    userService.deleteById(id);
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
