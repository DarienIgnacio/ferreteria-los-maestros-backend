package ferreteria_backend.controller;

import ferreteria_backend.model.Producto;
import ferreteria_backend.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
  @Autowired
  private ProductoService service;

  @GetMapping
  public List<Producto> listar() { return service.listar(); }

  @GetMapping("/{id}")
  public Producto buscar(@PathVariable Long id) { return service.buscar(id); }

  @PostMapping
  public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(producto));
  }

  @PutMapping("/{id}")
  public Producto actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto) {
    return service.actualizar(id, producto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}