package ferreteria_backend.service;

import ferreteria_backend.dto.CompraRequest;
import ferreteria_backend.dto.ItemCompraRequest;
import ferreteria_backend.exception.RecursoNoEncontradoException;
import ferreteria_backend.model.Boleta;
import ferreteria_backend.model.DetalleBoleta;
import ferreteria_backend.model.Producto;
import ferreteria_backend.repository.BoletaRepository;
import ferreteria_backend.repository.ProductoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoletaService {
  @Autowired
  private BoletaRepository boletaRepository;

  @Autowired
  private ProductoRepository productoRepository;

  public List<Boleta> listarTodas() {
    return boletaRepository.findAll();
  }

  public Boleta buscar(Long id) {
    return boletaRepository.findById(id).orElseThrow(() ->
      new RecursoNoEncontradoException("Boleta/Orden no encontrada: " + id));
  }

  public List<Boleta> listarPorCliente(String correo) {
    return boletaRepository.findByClienteCorreoIgnoreCase(correo);
  }

  @Transactional
  public Boleta generarCompra(String clienteCorreo, CompraRequest request) {
    Boleta boleta = new Boleta();
    boleta.setClienteCorreo(clienteCorreo);
    boleta.setFecha(LocalDateTime.now());

    int totalGeneral = 0;

    for (ItemCompraRequest item : request.items()) {
      Producto producto = productoRepository.findById(item.productoId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + item.productoId()));

      if (producto.getStock() < item.cantidad()) {
        throw new IllegalArgumentException("Stock insuficiente para: " + producto.getNombre());
      }

      // Descontar stock en inventario
      producto.setStock(producto.getStock() - item.cantidad());
      productoRepository.save(producto);

      int subtotal = producto.getPrecio() * item.cantidad();
      totalGeneral += subtotal;

      DetalleBoleta detalle = new DetalleBoleta();
      detalle.setBoleta(boleta);
      detalle.setProducto(producto);
      detalle.setCantidad(item.cantidad());
      detalle.setPrecioUnitario(producto.getPrecio());
      detalle.setSubtotal(subtotal);

      boleta.getDetalles().add(detalle);
    }

    boleta.setTotal(totalGeneral);
    return boletaRepository.save(boleta);
  }
}