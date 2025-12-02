package proyecto.biblioteca3.repository;

import proyecto.biblioteca3.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repositorio JPA para operaciones de acceso a datos de usuarios.
 * Proporciona métodos CRUD automáticos y consultas personalizadas para la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCedula(String cedula);
}

