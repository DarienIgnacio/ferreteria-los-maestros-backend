package ferreteria_auth.service;

import ferreteria_auth.dto.*;
import ferreteria_auth.model.Usuario;
import ferreteria_auth.repository.UsuarioRepository;
import ferreteria_auth.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UsuarioRepository usuarios;
  private final PasswordEncoder encoder;
  private final JwtService jwt;

  public AuthService(UsuarioRepository usuarios,
      PasswordEncoder encoder, JwtService jwt) {
    this.usuarios = usuarios;
    this.encoder = encoder;
    this.jwt = jwt;
  }

  public LoginResponse login(LoginRequest request) {
    Usuario usuario = usuarios.findByCorreoIgnoreCase(request.correo())
      .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
    if (!encoder.matches(request.password(), usuario.getPassword())) {
      throw new BadCredentialsException("Credenciales inválidas");
    }
    return new LoginResponse(
      jwt.generar(usuario),
      usuario.getNombre(),
      usuario.getCorreo(),
      usuario.getRol()
    );
  }

  public LoginResponse registrarCliente(RegistroRequest request) {
    if (usuarios.existsByCorreoIgnoreCase(request.correo())) {
      throw new IllegalArgumentException("El correo ya se encuentra registrado");
    }
    Usuario nuevo = new Usuario();
    nuevo.setNombre(request.nombre());
    nuevo.setCorreo(request.correo());
    nuevo.setPassword(encoder.encode(request.password()));
    nuevo.setRol("CLIENTE");
    usuarios.save(nuevo);

    return new LoginResponse(
      jwt.generar(nuevo),
      nuevo.getNombre(),
      nuevo.getCorreo(),
      nuevo.getRol()
    );
  }
}