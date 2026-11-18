package ferreteria_auth.config;
 

import ferreteria_auth.model.*;
import ferreteria_auth.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatosIniciales {
  @Bean
  CommandLineRunner usuarioInicial(UsuarioRepository repo, PasswordEncoder encoder) {
    return args -> {
      if (repo.findByCorreoIgnoreCase("admin@ferreteria.cl").isEmpty()) {
        Usuario admin = new Usuario();
        admin.setNombre("Administrador Maestro");
        admin.setCorreo("admin@ferreteria.cl");
        admin.setPassword(encoder.encode("Admin123!"));
        admin.setRol("ADMIN");
        repo.save(admin);
      }
      if (repo.findByCorreoIgnoreCase("vendedor@ferreteria.cl").isEmpty()) {
        Usuario vendedor = new Usuario();
        vendedor.setNombre("Juan Vendedor");
        vendedor.setCorreo("vendedor@ferreteria.cl");
        vendedor.setPassword(encoder.encode("Vendedor123!"));
        vendedor.setRol("VENDEDOR");
        repo.save(vendedor);
      }
      if (repo.findByCorreoIgnoreCase("cliente@ferreteria.cl").isEmpty()) {
        Usuario cliente = new Usuario();
        cliente.setNombre("Pedro Cliente");
        cliente.setCorreo("cliente@ferreteria.cl");
        cliente.setPassword(encoder.encode("Cliente123!"));
        cliente.setRol("CLIENTE");
        repo.save(cliente);
      }
    };
  }
}