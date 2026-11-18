package ferreteria_backend.service;

import ferreteria_backend.exception.RecursoNoEncontradoException;
import ferreteria_backend.model.Producto;
import ferreteria_backend.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
  @Autowired
  private ProductoRepository repository;

  public List<Producto> listar() {
    return repository.findAll();
  }

  public Producto buscar(Long id) {
    return repository.findById(id).orElseThrow(() ->
      new RecursoNoEncontradoException("Producto no encontrado: " + id));
  }

  public Producto guardar(Producto producto) {
    if (repository.existsByCodigoIgnoreCase(producto.getCodigo())) {
      throw new IllegalArgumentException("Ya existe un producto con el código: " + producto.getCodigo());
    }
    return repository.save(producto);
  }

  public Producto actualizar(Long id, Producto datos) {
    Producto actual = buscar(id);
    actual.setNombre(datos.getNombre());
    actual.setDescripcion(datos.getDescripcion());
    actual.setPrecio(datos.getPrecio());
    actual.setStock(datos.getStock());
    actual.setImagen(datos.getImagen());
    actual.setCategoria(datos.getCategoria());
    return repository.save(actual);
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }
}