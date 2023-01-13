package com.kpi.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserRegistrationDto {
  String name;
  String email;
  String password;
  String phoneNumber;
  Boolean isSpecialist;
}
