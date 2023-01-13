package com.kpi.service;

import com.kpi.domain.PasswordRecovery;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.PasswordRecoveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
  private final PasswordRecoveryRepository repository;

  public PasswordRecovery getById(Integer id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "Password recovery message with given id was not found!"));
  }
}
