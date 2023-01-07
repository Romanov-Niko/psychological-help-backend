package com.kpi.service;

import com.kpi.config.MailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender sender;
  private final MailProperties properties;

  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(properties.getUsername());
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    sender.send(message);
  }
}
