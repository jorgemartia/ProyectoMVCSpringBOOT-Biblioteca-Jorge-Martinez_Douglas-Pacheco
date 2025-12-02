package proyecto.biblioteca3.dto;

import lombok.Data;

/**
 * DTO para solicitudes de creación de préstamos.
 */

@Data
public class PrestamoRequest {
    private Integer usuarioId;
    private Integer libroId;
}
