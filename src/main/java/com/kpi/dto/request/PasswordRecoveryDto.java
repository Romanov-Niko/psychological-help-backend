package com.kpi.dto.request;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PasswordRecoveryDto {
  String email;
  UUID message;
  String password;
}
