package proyecto.biblioteca3.repository;

import proyecto.biblioteca3.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repositorio JPA para operaciones de acceso a datos de libros.
 * Proporciona métodos CRUD automáticos y consultas personalizadas para la entidad Libro.
 */
@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByCantidadDisponibleGreaterThan(Integer cantidad);
}