package com.kpi.service;

import com.kpi.domain.Appointment;
import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.dto.request.AppointmentRequestDto;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.AppointmentRepository;
import com.kpi.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

  private final AppointmentRepository repository;

  private final UserRepository userRepository;

  public Appointment getById(Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("Appointment with given id was not found!"));
  }

  public List<Appointment> getAllBySpecialistId(Integer id) {
    return repository.findAllBySpecialistId(id);
  }

  public List<Appointment> getAllByPatientId(Integer id) {
    return repository.findAllByPatientId(id);
  }

  public Appointment save(AppointmentRequestDto dto) {
    User specialist =
        userRepository
            .findByIdAndRoleName(dto.getSpecialistId(), RoleName.SPECIALIST)
            .orElseThrow(
                () -> new UserNotFoundException("Specialist with given id was not found!"));
    User patient =
        userRepository
            .findByIdAndRoleName(dto.getPatientId(), RoleName.PATIENT)
            .orElseThrow(() -> new UserNotFoundException("Patient with given id was not found!"));
    Appointment appointment =
        Appointment.builder()
            .patient(patient)
            .specialist(specialist)
            .dateTime(dto.getDateTime())
            .canceled(dto.getCanceled())
            .build();
    return repository.save(appointment);
  }

  public Appointment update(AppointmentRequestDto dto, Integer id) {
    User specialist =
        userRepository
            .findByIdAndRoleName(dto.getSpecialistId(), RoleName.SPECIALIST)
            .orElseThrow(
                () -> new UserNotFoundException("Specialist with given id was not found!"));
    User patient =
        userRepository
            .findByIdAndRoleName(dto.getPatientId(), RoleName.PATIENT)
            .orElseThrow(() -> new UserNotFoundException("Patient with given id was not found!"));
    Appointment appointment =
        Appointment.builder()
            .id(id)
            .patient(patient)
            .specialist(specialist)
            .dateTime(dto.getDateTime())
            .canceled(dto.getCanceled())
            .build();
    return repository.save(appointment);
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }
}
