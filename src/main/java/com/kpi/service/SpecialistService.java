package com.kpi.service;

import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.RoleRepository;
import com.kpi.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialistService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public List<User> getAllSpecialists() {
    return userRepository.findAllByRoleName(RoleName.SPECIALIST);
  }

  public User getSpecialistById(Integer id) {
    return userRepository
        .findByIdAndRoleName(id, RoleName.SPECIALIST)
        .orElseThrow(() -> new UserNotFoundException("Specialist with given id does not exist"));
  }
}
