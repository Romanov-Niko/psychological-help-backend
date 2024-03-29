package com.kpi.repository;

import com.kpi.domain.Role;
import com.kpi.domain.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(RoleName name);
}
