package com.kpi.service;

import com.kpi.domain.User;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public User getById(Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));
  }

  public List<User> getAll() {
    return repository.findAll();
  }

  public User getByEmailAndPassword(String email, String password) {
    return repository
        .findByEmail(email)
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
        .orElseThrow(
            () -> new UserNotFoundException("User with given email and password was not found!"));
  }
}
