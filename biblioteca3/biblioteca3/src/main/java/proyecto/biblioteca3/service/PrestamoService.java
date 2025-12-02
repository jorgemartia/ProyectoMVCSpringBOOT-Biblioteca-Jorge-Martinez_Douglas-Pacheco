package proyecto.biblioteca3.service;

import proyecto.biblioteca3.model.*;
import proyecto.biblioteca3.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Servicio para la lógica de negocio de préstamos.
 * Gestiona operaciones CRUD sobre préstamos de libros.
 */
@Service
@RequiredArgsConstructor
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> obtenerPorUsuario(Integer usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public Prestamo obtenerPorId(Integer id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public Prestamo guardar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Prestamo actualizar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Integer id) {
        prestamoRepository.deleteById(id);
    }
}