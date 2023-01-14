package com.kpi.controller;

import com.kpi.dto.request.PasswordRecoveryDto;
import com.kpi.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password/recovery")
@RequiredArgsConstructor
public class PasswordRecoveryController {
  private final EmailService service;

  @PostMapping("/send")
  public void passwordRecovery(@RequestParam String email) {
    service.sendPasswordRecoveryMessage(email);
  }

  @PostMapping("/change")
  public void updatePassword(@RequestBody PasswordRecoveryDto dto) {
    service.updatePassword(dto);
  }
}
