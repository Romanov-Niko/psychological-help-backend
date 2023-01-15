package com.kpi.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column private String name;
  @Column private String email;
  @Column private String password;
  @Column private String phoneNumber;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToOne
  @JoinColumn(name = "image")
  private FileDB image;
}
