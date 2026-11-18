package ferreteria_auth.dto;

import jakarta.validation.constraints.*;

public record RegistroRequest(
  @NotBlank String nombre,
  @NotBlank @Email String correo,
  @NotBlank @Size(min = 6) String password
) {}