package ferreteria_auth.controller;

import ferreteria_auth.dto.*;
import ferreteria_auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService auth;

  public AuthController(AuthService auth) {
    this.auth = auth;
  }

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    return auth.login(request);
  }

  @PostMapping("/registro")
  public ResponseEntity<LoginResponse> registro(@Valid @RequestBody RegistroRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(auth.registrarCliente(request));
  }
}