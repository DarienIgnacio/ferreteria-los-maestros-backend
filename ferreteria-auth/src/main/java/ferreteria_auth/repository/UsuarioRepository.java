package ferreteria_auth.repository;

import ferreteria_auth.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByCorreoIgnoreCase(String correo);
  boolean existsByCorreoIgnoreCase(String correo);
}