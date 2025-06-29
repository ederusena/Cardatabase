package br.Cardatabase.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.Cardatabase.CardatabaseApplication;
import br.Cardatabase.domain.AccountCredentialsRecord;
import br.Cardatabase.services.JwtService;

@RestController
public class LoginController {
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

  public LoginController(JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<?> getToken(@RequestBody AccountCredentialsRecord credentials) {
    logger.info("LoginController.getToken() called with credentials: " + credentials);
    UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(),
        credentials.password());
    Authentication auth = authenticationManager.authenticate(creds);

    // Generate token
    String jwts = jwtService.getToken(auth.getName());

    // Build response with the generated token
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
        "Bearer" + jwts).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
            "Authorization")
        .build();
  }
}
