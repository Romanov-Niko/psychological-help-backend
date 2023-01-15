package com.kpi.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProfileUpdateRequestDto {
  String name;
  String email;
  String phoneNumber;
}
