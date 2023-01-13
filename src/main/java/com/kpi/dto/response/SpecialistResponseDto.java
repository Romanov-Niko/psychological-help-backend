package com.kpi.dto.response;

import com.kpi.domain.Specialization;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class SpecialistResponseDto {
  Integer id;
  String name;
  String email;
  String phoneNumber;
  String role;
  List<Specialization> specializations;
}
