package com.kpi.dto.response;

import com.kpi.domain.RoleName;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class RoleResponseDto {
  Integer id;
  RoleName name;
}
