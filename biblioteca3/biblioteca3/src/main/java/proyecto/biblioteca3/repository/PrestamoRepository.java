package proyecto.biblioteca3.repository;

import proyecto.biblioteca3.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio JPA para operaciones de acceso a datos de préstamos.
 * Proporciona métodos CRUD automáticos y consultas personalizadas para la entidad Prestamo.
 */
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByUsuarioId(Integer usuarioId);
    List<Prestamo> findByLibroId(Integer libroId);
    List<Prestamo> findByEstado(Prestamo.EstadoPrestamo estado);
}