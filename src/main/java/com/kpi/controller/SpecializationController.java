package com.kpi.controller;

import com.kpi.domain.Specialization;
import com.kpi.service.SpecializationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specializations")
@RequiredArgsConstructor
public class SpecializationController {

  private final SpecializationService service;

  @GetMapping("/")
  public List<Specialization> getAll() {
    return service.getAll();
  }
}
