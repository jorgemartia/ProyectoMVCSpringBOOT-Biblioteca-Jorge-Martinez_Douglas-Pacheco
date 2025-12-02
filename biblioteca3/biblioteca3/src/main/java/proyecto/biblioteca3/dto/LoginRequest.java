package proyecto.biblioteca3.dto;

import lombok.Data;

/**
 * DTO para solicitudes de autenticación de usuarios.
 */
@Data
public class LoginRequest {
    private String email;
    private String clave;
}