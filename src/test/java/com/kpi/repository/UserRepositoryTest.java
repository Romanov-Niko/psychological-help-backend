package com.kpi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kpi.domain.Role;
import com.kpi.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class UserRepositoryTest {

  @Autowired private UserRepository repository;

  @Test
  @Sql({"/fixtures/users_fixture.sql"})
  void shouldFindUserByEmail() {
    Role role = new Role();
    role.setId(1);
    role.setName("SPECIALIST");
    User expected = new User();
    expected.setId(1);
    expected.setName("user_1");
    expected.setEmail("email_1");
    expected.setPassword("$2a$10$I0tK3uyYb2EnGYHCAl.fo.PdxSzhzWNY2oxQpLPL.oJPU6gAAqmcO");
    expected.setPhoneNumber("phone_number_1");
    expected.setRole(role);
    expected.setIsSpecialist(true);

    Optional<User> actual = repository.findByEmail("email_1");

    assertTrue(actual.isPresent());
    assertEquals(expected, actual.get());
  }

  @Test
  void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
    Optional<User> actual = repository.findByEmail("non existent email");

    assertTrue(actual.isEmpty());
  }
}
