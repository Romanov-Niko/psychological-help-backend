package com.kpi.service;

import com.kpi.domain.User;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final JwtEncoder encoder;

  public String generateToken(User user) {
    Instant now = Instant.now();
    String scope = user.getRole().getName().name();
    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(7, ChronoUnit.DAYS))
            .subject(user.getEmail())
            .claim("scope", scope)
            .build();
    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
