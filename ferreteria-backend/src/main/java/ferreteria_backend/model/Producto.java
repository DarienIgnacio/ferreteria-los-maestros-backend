package ferreteria_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String codigo;

  @NotBlank
  @Column(nullable = false)
  private String nombre;

  private String descripcion;

  @NotNull
  @Min(1)
  @Column(nullable = false)
  private Integer precio;

  @NotNull
  @Min(0)
  @Column(nullable = false)
  private Integer stock;

  private String imagen;

  @ManyToOne(optional = false)
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;
}