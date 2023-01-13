package com.kpi.service;

import com.kpi.domain.Role;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository repository;

  public Role getById(Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("Article with given id was not found!"));
  }

  public List<Role> getAll() {
    return repository.findAll();
  }
}
