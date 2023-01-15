package com.kpi.service;

import com.kpi.domain.Appointment;
import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.dto.request.AppointmentRequestDto;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.AppointmentRepository;
import com.kpi.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;

  private final UserRepository userRepository;

  public Appointment getById(Integer id) {
    return appointmentRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("Appointment with given id was not found!"));
  }

  public List<Appointment> getAllBySpecialistId(Integer id) {
    return appointmentRepository.findAllBySpecialistId(id);
  }

  public List<Appointment> getAllByPatientId(Integer id) {
    return appointmentRepository.findAllByPatientId(id);
  }

  public List<Appointment> getAllBySpecialistIdAndDate(Integer id, LocalDateTime dateTime) {
    LocalDateTime from = dateTime.toLocalDate().atStartOfDay();
    LocalDateTime to = from.plusDays(1);
    return appointmentRepository.findAllBySpecialistIdAndDateTimeBetween(id, from, to);
  }

  public List<Appointment> getAll(Integer id) {
    User user = userRepository.findById(id).orElseThrow();
    if (user.getRole().getName() == RoleName.PATIENT) {
      return getAllByPatientId(id);
    }
    return getAllBySpecialistId(id);
  }

  public Appointment save(AppointmentRequestDto dto, Integer specialistId) {
    User specialist =
        userRepository
            .findById(specialistId)
            .orElseThrow(
                () -> new UserNotFoundException("Specialist with given id was not found!"));
    Appointment appointment =
        Appointment.builder()
            .specialist(specialist)
            .dateTime(dto.getDateTime())
            .canceled(Boolean.FALSE)
            .free(Boolean.TRUE)
            .build();
    return appointmentRepository.save(appointment);
  }

  public Appointment checkIn(Integer appointmentId, Integer patientId) {
    User patient =
        userRepository
            .findById(patientId)
            .orElseThrow(() -> new UserNotFoundException("Patient with given id was not found!"));
    Appointment appointment =
        appointmentRepository
            .findById(appointmentId)
            .orElseThrow(
                () -> new UserNotFoundException("Appointment with given id was not found!"));
    appointment.setPatient(patient);
    appointment.setFree(Boolean.FALSE);
    return appointmentRepository.save(appointment);
  }

  public void deleteById(Integer id) {
    appointmentRepository.deleteById(id);
  }
}
