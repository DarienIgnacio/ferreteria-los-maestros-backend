package ferreteria_auth.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Object> credencialesInvalidas() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body(Map.of("mensaje", "Credenciales inválidas"));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> errorNegocio(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(Map.of("mensaje", e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> datosInvalidos(MethodArgumentNotValidException e) {
    String msg = e.getBindingResult().getFieldErrors().stream()
      .findFirst()
      .map(err -> err.getField() + ": " + err.getDefaultMessage())
      .orElse("Datos inválidos");
    return ResponseEntity.badRequest().body(Map.of("mensaje", msg));
  }
}