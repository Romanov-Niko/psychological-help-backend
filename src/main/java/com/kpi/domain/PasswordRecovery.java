package com.kpi.domain;

import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "password_recovery")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRecovery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column private UUID message;
}
