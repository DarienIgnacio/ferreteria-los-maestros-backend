package ferreteria_backend.repository;

import ferreteria_backend.model.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Optional<Categoria> findByNombreIgnoreCase(String nombre);
}