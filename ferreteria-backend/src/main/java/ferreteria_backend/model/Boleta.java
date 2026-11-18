package ferreteria_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boletas")
@Getter
@Setter
public class Boleta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime fecha = LocalDateTime.now();

  @Column(nullable = false)
  private String clienteCorreo;

  @Column(nullable = false)
  private Integer total;

  @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<DetalleBoleta> detalles = new ArrayList<>();
}