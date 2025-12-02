package proyecto.biblioteca3.service;

import proyecto.biblioteca3.dto.*;
import proyecto.biblioteca3.model.*;
import proyecto.biblioteca3.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * Servicio para la lógica de negocio de usuarios.
 * Gestiona registro, autenticación y operaciones CRUD sobre usuarios.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en el sistema.
     * Valida que el email y cédula sean únicos.
     * @param request datos del usuario a registrar
     * @return el usuario registrado
     */
    public Usuario registrarUsuario(RegistroRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (usuarioRepository.findByCedula(request.getCedula()).isPresent()) {
            throw new RuntimeException("La cédula ya está registrada");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .cedula(request.getCedula())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .clave(passwordEncoder.encode(request.getClave()))
                .rol(Usuario.RolUsuario.USUARIO)
                .activo(true)
                .fechaRegistro(LocalDateTime.now())
                .build();

        return usuarioRepository.save(usuario);
    }
    /**
     * Autentica un usuario verificando email y contraseña.
     * @param email email del usuario
     * @param clave contraseña sin encriptar
     * @return Optional con el usuario si las credenciales son correctas
     */
    public Optional<Usuario> autenticar(String email, String clave) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && passwordEncoder.matches(clave, usuario.get().getClave())) {
            return usuario;
        }
        return Optional.empty();
    }
     /** Busca un usuario por su ID */
    public Optional<Usuario> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }
    /** Obtiene todos los usuarios del sistema */
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
}
