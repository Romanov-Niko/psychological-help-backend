package com.kpi.controller;

import com.kpi.domain.FileDB;
import com.kpi.domain.Specialization;
import com.kpi.domain.User;
import com.kpi.dto.response.SpecialistResponseDto;
import com.kpi.service.SpecialistService;
import com.kpi.service.SpecializationService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialists")
@RequiredArgsConstructor
public class SpecialistController {
  private final SpecialistService service;
  private final SpecializationService specializationService;

  @GetMapping("/")
  public List<SpecialistResponseDto> getAllSpecialists() {
    return service.getAllSpecialists().stream()
        .map(
            user -> {
              List<Specialization> specializations =
                  specializationService.getAllBySpecialistId(user.getId());
              return convertToSpecialistDto(user, specializations);
            })
        .toList();
  }

  @GetMapping("/{id}")
  public SpecialistResponseDto getSpecialistById(@PathVariable Integer id) {
    User user = service.getSpecialistById(id);
    List<Specialization> specializations = specializationService.getAllBySpecialistId(user.getId());
    return convertToSpecialistDto(user, specializations);
  }

  private SpecialistResponseDto convertToSpecialistDto(
      User user, List<Specialization> specializations) {
    return SpecialistResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole().getName().name())
        .specializations(specializations)
        .imageId(Optional.ofNullable(user.getImage()).map(FileDB::getId).orElse(null))
        .build();
  }
}
