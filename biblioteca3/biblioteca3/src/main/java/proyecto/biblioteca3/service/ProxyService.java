package proyecto.biblioteca3.service;

import org.springframework.stereotype.Service;
import proyecto.biblioteca3.model.Usuario;
import lombok.RequiredArgsConstructor;

/**
 * Servicio Proxy para control de acceso y permisos del sistema.
 * Implementa el patrón Proxy para gestionar autorizaciones basadas en roles.
 */
@Service
@RequiredArgsConstructor
public class ProxyService {
    
    /** Verifica si el usuario tiene rol de administrador y está activo */
    public boolean esAdministrador(Usuario usuario) {
        return usuario != null && 
               usuario.getRol() == Usuario.RolUsuario.ADMIN && 
               Boolean.TRUE.equals(usuario.getActivo());
    }
    
    /** Verifica si el usuario puede devolver un préstamo específico */
    public boolean puedeDevolver(Usuario usuario, Integer prestamoId) {
        if (esAdministrador(usuario)) {
            return true;
        }

        return usuario != null && Boolean.TRUE.equals(usuario.getActivo());
    }
    
    /** Verifica permisos para gestionar libros (solo ADMIN) */
    public boolean puedeGestionarLibros(Usuario usuario) {
        return esAdministrador(usuario);
    }
    
    /** Verifica permisos para ver todos los préstamos (solo ADMIN) */
    public boolean puedeVerTodosPrestamos(Usuario usuario) {
        return esAdministrador(usuario);
    }
    
    /** Verifica permisos para gestionar usuarios (solo ADMIN) */
    public boolean puedeGestionarUsuarios(Usuario usuario) {
        return esAdministrador(usuario);
    }
}