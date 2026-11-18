package ferreteria_backend.controller;

import ferreteria_backend.dto.CompraRequest;
import ferreteria_backend.model.Boleta;
import ferreteria_backend.service.BoletaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {
  @Autowired
  private BoletaService service;

  @GetMapping
  public List<Boleta> listarTodas() { return service.listarTodas(); }

  @GetMapping("/{id}")
  public Boleta buscar(@PathVariable Long id) { return service.buscar(id); }

  @GetMapping("/mis-compras")
  public List<Boleta> misCompras(Authentication auth) {
    return service.listarPorCliente(auth.getName());
  }

  @PostMapping
  public ResponseEntity<Boleta> comprar(@Valid @RequestBody CompraRequest request, Authentication auth) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(service.generarCompra(auth.getName(), request));
  }
}