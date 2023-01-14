package com.kpi.controller;

import com.kpi.domain.Appointment;
import com.kpi.domain.User;
import com.kpi.dto.request.UserRequestDto;
import com.kpi.dto.response.AppointmentResponseDto;
import com.kpi.dto.response.UserResponseDto;
import com.kpi.service.AppointmentService;
import com.kpi.service.UserService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
  private final AppointmentService appointmentService;
  private final UserService userService;

  @GetMapping("/")
  public UserResponseDto getById(Principal principal) {
    Integer id = Integer.parseInt(principal.getName());
    User user = userService.getById(id);
    return convertToUserDto(user);
  }

  @GetMapping("/appointments")
  public List<AppointmentResponseDto> getAllAppointments(Principal principal) {
    Integer id = Integer.parseInt(principal.getName());
    List<Appointment> appointments = appointmentService.getAll(id);
    return appointments.stream().map(this::convertToDto).toList();
  }

  @PutMapping("/")
  public UserResponseDto update(@RequestBody UserRequestDto dto, Principal principal) {
    Integer id = Integer.parseInt(principal.getName());
    return convertToUserDto(userService.update(dto, id));
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

  private UserResponseDto convertToUserDto(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole().getName().name())
        .build();
  }
}
