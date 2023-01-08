package com.kpi.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ArticleRequestDto {
  Integer specialistId;
  LocalDateTime dateTime;
  String title;
  String articleText;
}
