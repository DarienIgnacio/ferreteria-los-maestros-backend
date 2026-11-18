package ferreteria_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ferreteria_backend.model.Categoria;
import ferreteria_backend.model.Producto;
import ferreteria_backend.repository.CategoriaRepository;
import ferreteria_backend.repository.ProductoRepository;

@Configuration
public class DatosInicialesTienda {

  @Bean
  CommandLineRunner cargarCatalogoInicial(CategoriaRepository catRepo, ProductoRepository prodRepo) {
    return args -> {
      if (catRepo.count() == 0) {
        Categoria electricas = new Categoria();
        electricas.setNombre("Herramientas Eléctricas");
        electricas.setDescripcion("Taladros, sierras y esmeriles");
        catRepo.save(electricas);

        Categoria manuales = new Categoria();
        manuales.setNombre("Herramientas Manuales");
        manuales.setDescripcion("Martillos, destornilladores y llaves");
        catRepo.save(manuales);

        Producto p1 = new Producto();
        p1.setCodigo("TAL-001");
        p1.setNombre("Taladro Percutor 650W");
        p1.setDescripcion("Taladro percutor velocidad variable con mandril 13mm");
        p1.setPrecio(45990);
        p1.setStock(15);
        p1.setImagen("https://images.unsplash.com/photo-1504148455328-c376907d081c?w=400");
        p1.setCategoria(electricas);
        prodRepo.save(p1);

        Producto p2 = new Producto();
        p2.setCodigo("MAR-002");
        p2.setNombre("Martillo Carpintero Mango Fibra");
        p2.setDescripcion("Martillo de acero forjado 16 oz antideslizante");
        p2.setPrecio(12990);
        p2.setStock(30);
        p2.setImagen("https://images.unsplash.com/photo-1586864387967-d02ef85d93e8?w=400");
        p2.setCategoria(manuales);
        prodRepo.save(p2);
      }
    };
  }
}