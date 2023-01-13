package com.kpi.service;

import com.kpi.domain.Role;
import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.dto.request.UserRegistrationDto;
import com.kpi.dto.request.UserRequestDto;
import com.kpi.exception.RoleNotFoundException;
import com.kpi.exception.UserNotFoundException;
import com.kpi.exception.UserWithGivenEmailExistException;
import com.kpi.repository.RoleRepository;
import com.kpi.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public User getById(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public List<User> getAllSpecialists() {
    return userRepository.findAllByRoleName(RoleName.SPECIALIST);
  }

  public User getByEmailAndPassword(String email, String password) {
    return userRepository
        .findByEmail(email)
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
        .orElseThrow(
            () -> new UserNotFoundException("User with given email and password was not found!"));
  }

  public User save(UserRequestDto dto) {
    userRepository
        .findByEmail(dto.getEmail())
        .ifPresent(
            user -> {
              throw new UserWithGivenEmailExistException("User with given email already exist!");
            });
    Role role =
        roleRepository
            .findById(dto.getRoleId())
            .orElseThrow(() -> new RoleNotFoundException("Role not found!"));
    User user =
        User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .phoneNumber(dto.getPhoneNumber())
            .role(role)
            .build();
    return userRepository.save(user);
  }

  public User registration(UserRegistrationDto dto) {
    userRepository
        .findByEmail(dto.getEmail())
        .ifPresent(
            user -> {
              throw new UserWithGivenEmailExistException("User with given email already exist!");
            });
    Role role;
    if (dto.getIsSpecialist()) {
      role = roleRepository.findByName(RoleName.SPECIALIST).orElseThrow();
    } else {
      role = roleRepository.findByName(RoleName.PATIENT).orElseThrow();
    }
    User user =
        User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .phoneNumber(dto.getPhoneNumber())
            .role(role)
            .build();
    return userRepository.save(user);
  }

  public User update(UserRequestDto dto, Integer id) {
    Role role =
        roleRepository
            .findById(dto.getRoleId())
            .orElseThrow(() -> new RoleNotFoundException("Role not found!"));
    User user =
        User.builder()
            .id(id)
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .phoneNumber(dto.getPhoneNumber())
            .role(role)
            .build();
    return userRepository.save(user);
  }

  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }
}
