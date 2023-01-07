package com.kpi.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserResponseDto {
  Integer id;
  String name;
  String email;
  String phoneNumber;
  String role;
}
