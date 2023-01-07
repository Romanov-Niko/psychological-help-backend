package com.kpi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.kpi.config.MailProperties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

  @RegisterExtension
  static GreenMailExtension greenMail =
      new GreenMailExtension(ServerSetupTest.SMTP)
          .withConfiguration(GreenMailConfiguration.aConfig().withUser("user", "password"))
          .withPerMethodLifecycle(false);

  @Autowired private EmailService service;

  @Autowired private MailProperties properties;

  @Test
  void shouldSendEmailWithPassedAddressSubjectAndText() throws MessagingException {
    String from = properties.getUsername();
    String to = "tests@gmail.com";
    String subject = "Test subject";
    String text = "Here is some text for tests";

    service.sendSimpleMessage(to, subject, text);

    MimeMessage message = greenMail.getReceivedMessages()[0];
    assertEquals(1, message.getFrom().length);
    assertEquals(from, message.getFrom()[0].toString());
    assertEquals(1, message.getAllRecipients().length);
    assertEquals(to, message.getAllRecipients()[0].toString());
    assertEquals(subject, message.getSubject());
    assertEquals(text, GreenMailUtil.getBody(message));
  }
}
