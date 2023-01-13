package com.kpi.controller;

import com.kpi.domain.Appointment;
import com.kpi.dto.response.AppointmentResponseDto;
import com.kpi.service.AppointmentService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/specialist")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCOPE_SPECIALIST')")
public class SpecialistProfileController {

  private final AppointmentService service;

  @GetMapping("/appointments")
  public List<AppointmentResponseDto> getAllAppointments(Principal principal) {
    Integer id = Integer.parseInt(principal.getName());
    List<Appointment> appointments = service.getAllBySpecialistId(id);
    return appointments.stream().map(this::convertToDto).toList();
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
