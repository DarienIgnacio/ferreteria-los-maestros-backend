package ferreteria_backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CompraRequest(
  @NotEmpty @Valid List<ItemCompraRequest> items
) {}