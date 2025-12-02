package proyecto.biblioteca3.dto;

import lombok.*;
/**
 * DTO para respuestas estandarizadas de la API REST.
 * Encapsula el resultado de las operaciones con información de éxito, mensaje y datos.
 * 
 * @param <T> tipo de datos contenidos en la respuesta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponse<T> {
    private boolean exito;
    private String mensaje;
    private T datos;
    private String error;
}
