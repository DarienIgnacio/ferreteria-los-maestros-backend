package ferreteria_backend.repository;

import ferreteria_backend.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
  List<Producto> findByCategoriaId(Long categoriaId);
  boolean existsByCodigoIgnoreCase(String codigo);
}