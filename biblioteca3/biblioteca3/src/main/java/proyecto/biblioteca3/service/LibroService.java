package proyecto.biblioteca3.service;

import proyecto.biblioteca3.model.*;
import proyecto.biblioteca3.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime; // ✅ Agregar este import
import java.util.List;
import java.util.Optional;
/**
 * Servicio para la gestión de libros en la biblioteca.
 * Proporciona métodos para guardar, obtener, eliminar, prestar y devolver libros.
 */
@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;
     /**
     * Guarda o actualiza un libro en el sistema.
     * @param libro el libro a guardar
     * @return el libro guardado
     */
    @SuppressWarnings("UseSpecificCatch")
    public Libro guardar(Libro libro) {
        try {
            // Validaciones básicas
            if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
                throw new RuntimeException("El título del libro es requerido");
            }
            if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
                throw new RuntimeException("El autor del libro es requerido");
            }
            if (libro.getCantidadTotal() == null || libro.getCantidadTotal() <= 0) {
                throw new RuntimeException("La cantidad total debe ser mayor a 0");
            }
            
            if (libro.getId() == null) {
                // Nuevo libro: cantidad disponible = cantidad total
                libro.setCantidadDisponible(libro.getCantidadTotal());
                libro.setFechaIngreso(LocalDateTime.now());
            }
            return libroRepository.save(libro);
        } catch (Exception e) {
            System.err.println("Error en LibroService.guardar: " + e.getMessage());
            throw new RuntimeException("Error al guardar el libro: " + e.getMessage());
        }
    }
    
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    public Optional<Libro> obtenerPorId(Integer id) {
        return libroRepository.findById(id);
    }
    
    public void eliminar(Integer id) {
        libroRepository.deleteById(id);
    }
    /**
     * Reduce la cantidad disponible de un libro al prestarlo.
     * @param libroId ID del libro a prestar
     * @return true si el préstamo fue exitoso, false si no hay disponibilidad
     */
    @Transactional
    public boolean prestarLibro(Integer libroId) {
        Optional<Libro> libroOpt = libroRepository.findById(libroId);
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            if (libro.getCantidadDisponible() > 0) {
                libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
                libroRepository.save(libro);
                return true;
            }
        }
        return false;
    }
    /**
     * Incrementa la cantidad disponible de un libro al devolverlo.
     * @param libroId ID del libro devuelto
     */
    @Transactional
    public void devolverLibro(Integer libroId) {
        Optional<Libro> libroOpt = libroRepository.findById(libroId);
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            if (libro.getCantidadDisponible() < libro.getCantidadTotal()) {
                libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
                libroRepository.save(libro);
            }
        }
    }
}