package com.kpi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.kpi.domain.Role;
import com.kpi.domain.RoleName;
import com.kpi.domain.User;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserRepository repository;
  @Mock private PasswordEncoder passwordEncoder;
  @InjectMocks private UserService service;

  @Test
  void shouldReturnUserById() {
    Role role = new Role();
    role.setId(1);
    role.setName(RoleName.SPECIALIST);
    User user = new User();
    user.setId(1);
    user.setName("user_1");
    user.setEmail("email_1");
    user.setPassword("password");
    user.setPhoneNumber("phone_number_1");
    user.setRole(role);
    when(repository.findById(1)).thenReturn(Optional.of(user));

    User result = service.getById(1);

    assertEquals(user, result);
  }

  @Test
  void shouldThrowExceptionIfUserWasNotFoundById() {
    String expectedMessage = "User with given id was not found!";
    when(repository.findById(1)).thenReturn(Optional.empty());

    Exception exception = assertThrows(UserNotFoundException.class, () -> service.getById(1));

    assertEquals(expectedMessage, exception.getMessage());
  }
}
