package com.kpi.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "specialist")
@Data
public class Specialist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User userData;

  @Column private Boolean verified;

  @ManyToMany
  @JoinTable(
      name = "specialist_specialization",
      joinColumns = @JoinColumn(name = "specialist_id"),
      inverseJoinColumns = @JoinColumn(name = "specialization_id"))
  private Set<Specialization> specializations;
}
