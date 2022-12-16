package com.kpi.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private User patient;

  @ManyToOne
  @JoinColumn(name = "specialist_id")
  private User specialist;

  @Column private LocalDateTime dateTime;
  @Column private Boolean canceled;
}
