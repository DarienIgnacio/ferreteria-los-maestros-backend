package ferreteria_backend.service;

import ferreteria_backend.exception.RecursoNoEncontradoException;
import ferreteria_backend.model.Categoria;
import ferreteria_backend.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
  @Autowired
  private CategoriaRepository repository;

  public List<Categoria> listar() {
    return repository.findAll();
  }

  public Categoria buscar(Long id) {
    return repository.findById(id).orElseThrow(() ->
      new RecursoNoEncontradoException("Categoría no encontrada: " + id));
  }

  public Categoria guardar(Categoria categoria) {
    return repository.save(categoria);
  }

  public Categoria actualizar(Long id, Categoria datos) {
    Categoria actual = buscar(id);
    actual.setNombre(datos.getNombre());
    actual.setDescripcion(datos.getDescripcion());
    return repository.save(actual);
  }

  public void eliminar(Long id) {
    repository.delete(buscar(id));
  }
}