package com.kpi.repository;

import com.kpi.domain.PasswordRecovery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Integer> {
  Optional<PasswordRecovery> findByMessageAndUserId(UUID message, Integer id);
}
