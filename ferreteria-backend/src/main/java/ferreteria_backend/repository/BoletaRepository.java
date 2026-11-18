package ferreteria_backend.repository;

import ferreteria_backend.model.Boleta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
  List<Boleta> findByClienteCorreoIgnoreCase(String correo);
}