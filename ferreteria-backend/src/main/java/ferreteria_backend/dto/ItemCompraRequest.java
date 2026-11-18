package ferreteria_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemCompraRequest(
  @NotNull Long productoId,
  @NotNull @Min(1) Integer cantidad
) {}