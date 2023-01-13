package com.kpi.repository;

import com.kpi.domain.Specialization;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
  @Query(
      value =
          "SELECT * FROM specialization JOIN specialist_specialization ss ON specialization.id = ss.specialization_id WHERE specialist_id = ?1",
      nativeQuery = true)
  List<Specialization> findAllBySpecialistId(Integer id);
}
