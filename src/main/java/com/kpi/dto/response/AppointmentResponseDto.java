package com.kpi.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AppointmentResponseDto {
  Integer id;

  Integer patientId;

  Integer specialistId;

  LocalDateTime dateTime;
  Boolean canceled;
}
