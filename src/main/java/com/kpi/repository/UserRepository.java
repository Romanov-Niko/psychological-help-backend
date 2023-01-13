package com.kpi.repository;

import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findByIdAndRoleName(Integer id, RoleName name);

  List<User> findAllByRoleName(RoleName name);
}
