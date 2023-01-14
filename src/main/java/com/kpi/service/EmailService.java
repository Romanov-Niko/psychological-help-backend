package com.kpi.service;

import com.kpi.config.MailProperties;
import com.kpi.domain.PasswordRecovery;
import com.kpi.domain.User;
import com.kpi.dto.request.PasswordRecoveryDto;
import com.kpi.exception.UserNotFoundException;
import com.kpi.repository.PasswordRecoveryRepository;
import com.kpi.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender sender;
  private final MailProperties properties;
  private final PasswordEncoder passwordEncoder;

  private final PasswordRecoveryRepository passwordRecoveryRepository;

  private final UserRepository userRepository;

  public void sendPasswordRecoveryMessage(String email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(properties.getUsername());
    message.setTo(email);
    message.setSubject("Password Recovery");
    UUID randomUUID = UUID.randomUUID();
    message.setText(randomUUID.toString());
    userRepository
        .findByEmail(email)
        .ifPresent(
            user -> {
              PasswordRecovery passwordRecovery =
                  PasswordRecovery.builder().user(user).message(randomUUID).build();
              passwordRecoveryRepository.save(passwordRecovery);
              sender.send(message);
            });
  }

  public void updatePassword(PasswordRecoveryDto dto) {
    User user =
        userRepository
            .findByEmail(dto.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User with given email was not found!"));

    passwordRecoveryRepository
        .findByMessageAndUserId(dto.getMessage(), user.getId())
        .ifPresent(
            passwordRecovery -> {
              user.setPassword(passwordEncoder.encode(dto.getPassword()));
              userRepository.save(user);
              passwordRecoveryRepository.deleteById(passwordRecovery.getId());
            });
  }
}
