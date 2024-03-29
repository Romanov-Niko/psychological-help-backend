package com.kpi.dto.request;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AppointmentRequestDto {
  LocalDateTime dateTime;
  Boolean free;
}
