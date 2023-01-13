package com.kpi.service;

import com.kpi.domain.Specialization;
import com.kpi.repository.SpecializationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationService {

  private final SpecializationRepository repository;

  public List<Specialization> getAll() {
    return repository.findAll();
  }

  public List<Specialization> getAllBySpecialistId(Integer id) {
    return repository.findAllBySpecialistId(id);
  }
}
