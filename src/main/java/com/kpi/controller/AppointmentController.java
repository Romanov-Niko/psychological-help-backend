package com.kpi.controller;

import com.kpi.domain.Appointment;
import com.kpi.dto.request.AppointmentRequestDto;
import com.kpi.dto.response.AppointmentResponseDto;
import com.kpi.service.AppointmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

  private final AppointmentService service;

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public AppointmentResponseDto getById(@PathVariable Integer id) {
    return convertToDto(service.getById(id));
  }

  @GetMapping("/specialist/{id}")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public List<AppointmentResponseDto> getAllBySpecialistId(@PathVariable Integer id) {
    List<Appointment> appointments = service.getAllBySpecialistId(id);
    return appointments.stream().map(this::convertToDto).toList();
  }

  @GetMapping("/patient/{id}")
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  public List<AppointmentResponseDto> getAllByPatientId(@PathVariable Integer id) {
    List<Appointment> appointments = service.getAllByPatientId(id);
    return appointments.stream().map(this::convertToDto).toList();
  }

  @PostMapping("/")
  @PreAuthorize("hasAnyAuthority('SCOPE_SPECIALIST', 'SCOPE_ADMIN')")
  public AppointmentResponseDto save(@RequestBody AppointmentRequestDto dto) {
    return convertToDto(service.save(dto));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_SPECIALIST', 'SCOPE_ADMIN')")
  public AppointmentResponseDto update(
      @RequestBody AppointmentRequestDto dto, @PathVariable Integer id) {
    return convertToDto(service.update(dto, id));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_SPECIALIST', 'SCOPE_ADMIN')")
  public void deleteById(@PathVariable Integer id) {
    service.deleteById(id);
  }

  private AppointmentResponseDto convertToDto(Appointment appointment) {
    return AppointmentResponseDto.builder()
        .id(appointment.getId())
        .patientId(appointment.getPatient().getId())
        .specialistId(appointment.getSpecialist().getId())
        .dateTime(appointment.getDateTime())
        .canceled(appointment.getCanceled())
        .build();
  }
}
