package com.kpi.dto.response;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ArticleResponseDto {
  Integer id;
  Integer specialistId;
  LocalDateTime dateTime;
  String title;
  String previewText;
  String articleText;
  Integer imageId;
}
