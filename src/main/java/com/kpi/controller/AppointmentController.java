package com.kpi.controller;

import com.kpi.domain.Appointment;
import com.kpi.domain.User;
import com.kpi.dto.request.AppointmentRequestDto;
import com.kpi.dto.response.AppointmentResponseDto;
import com.kpi.service.AppointmentService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

  @GetMapping("/by/date/{id}")
  public List<AppointmentResponseDto> getAllBySpecialistIdAndDate(
      @PathVariable Integer id,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime dateTime) {
    List<Appointment> appointments = service.getAllBySpecialistIdAndDate(id, dateTime);
    return appointments.stream().map(this::convertToDto).toList();
  }

  @PostMapping("/")
  @PreAuthorize("hasAnyAuthority('SCOPE_SPECIALIST', 'SCOPE_ADMIN')")
  public AppointmentResponseDto save(@RequestBody AppointmentRequestDto dto, Principal principal) {
    Integer id = Integer.parseInt(principal.getName());
    return convertToDto(service.save(dto, id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_PATIENT', 'SCOPE_ADMIN')")
  public AppointmentResponseDto checkIn(@PathVariable Integer id, Principal principal) {
    Integer userId = Integer.parseInt(principal.getName());
    return convertToDto(service.checkIn(id, userId));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_SPECIALIST', 'SCOPE_ADMIN')")
  public void deleteById(@PathVariable Integer id) {
    service.deleteById(id);
  }

  private AppointmentResponseDto convertToDto(Appointment appointment) {
    return AppointmentResponseDto.builder()
        .id(appointment.getId())
        .patientId(Optional.ofNullable(appointment.getPatient()).map(User::getId).orElse(null))
        .specialistId(appointment.getSpecialist().getId())
        .dateTime(appointment.getDateTime())
        .canceled(appointment.getCanceled())
        .free(appointment.getFree())
        .build();
  }
}
