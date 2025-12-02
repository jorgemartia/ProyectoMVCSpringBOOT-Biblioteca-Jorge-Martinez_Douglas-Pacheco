package proyecto.biblioteca3.config;

/**
 * Configuración de CORS para permitir solicitudes desde otros dominios.
 * 
 * <p>Habilita el acceso a los endpoints {@code /api/**} desde cualquier origen
 * con los métodos HTTP estándar.</p>
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
