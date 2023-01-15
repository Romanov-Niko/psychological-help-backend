package com.kpi.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "specialist_id")
  private User specialist;

  @Column private LocalDateTime dateTime;
  @Column private String title;
  @Column private String previewText;
  @Column private String articleText;

  @OneToOne
  @JoinColumn(name = "image")
  private FileDB image;
}
