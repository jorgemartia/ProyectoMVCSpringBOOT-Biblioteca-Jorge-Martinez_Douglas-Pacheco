package proyecto.biblioteca3.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import proyecto.biblioteca3.dto.*;
import proyecto.biblioteca3.model.*;
import proyecto.biblioteca3.service.*;
import proyecto.biblioteca3.command.*;
import proyecto.biblioteca3.validador.*;
import org.springframework.http.HttpStatus;

/**
 * Controlador REST para la gestión de libros.
 * Proporciona endpoints para operaciones CRUD sobre libros.
 */
@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LibroController {

        private final LibroService libroService;
        private final LibroCommand libroCommand;
        private final ValidacionUsuario validadorUsuarios;
        private final ValidacionPermiso validadorPermisos;
        private final ValidacionLibro validadorLibros;

         /**
         * Lista todos los libros del catálogo.
         * @return ResponseEntity con lista de libros
         */
        @GetMapping
        public ResponseEntity<ApiResponse<List<Libro>>> listar() {
                return ResponseEntity.ok(ApiResponse.<List<Libro>>builder()
                                .exito(true)
                                .datos(libroService.obtenerTodos())
                                .build());
        }

        /**
         * Obtiene un libro por su ID.
         * @param id ID del libro
         * @return ResponseEntity con el libro encontrado
         */
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<Libro>> obtener(@PathVariable Integer id) {
                try {
                        Libro libro = validadorLibros.validarExiste(id, "obtener");
                        return ResponseEntity.ok(ApiResponse.<Libro>builder()
                                        .exito(true)
                                        .datos(libro)
                                        .build());
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.notFound().build();
                }
        }
        /**
         * Crea un nuevo libro (solo ADMIN).
         * @param libro datos del libro a crear
         * @param usuarioId ID del usuario que realiza la operación
         * @return ResponseEntity con el libro creado
         */
        @PostMapping
        public ResponseEntity<ApiResponse<Libro>> crear(
                        @RequestBody Libro libro,
                        @RequestParam Integer usuarioId) {
                try {

                        Usuario usuario = validadorUsuarios.validarExiste(usuarioId);
                        validadorPermisos.validarGestionLibros(usuario);

                        Libro creado = libroCommand
                                        .configurar(libro, LibroCommand.TipoOperacion.AGREGAR)
                                        .ejecutar();

                        return ResponseEntity.ok(ApiResponse.<Libro>builder()
                                        .exito(true)
                                        .mensaje("Libro registrado exitosamente")
                                        .datos(creado)
                                        .build());
                } catch (IllegalArgumentException | IllegalStateException e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(ApiResponse.<Libro>builder()
                                                        .exito(false)
                                                        .mensaje(e.getMessage())
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(ApiResponse.<Libro>builder()
                                                        .exito(false)
                                                        .mensaje("Error al registrar libro: " + e.getMessage())
                                                        .build());
                }
        }
        /**
         * Actualiza un libro existente (solo ADMIN).
         * @param id ID del libro a actualizar
         * @param libro datos actualizados del libro
         * @param usuarioId ID del usuario que realiza la operación
         * @return ResponseEntity con el libro actualizado
         */

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Libro>> actualizar(
                        @PathVariable Integer id,
                        @RequestBody Libro libro,
                        @RequestParam Integer usuarioId) {
                try {

                        Usuario usuario = validadorUsuarios.validarExiste(usuarioId);
                        validadorPermisos.validarGestionLibros(usuario);
                        validadorLibros.validarExiste(id, "actualizar");

                        libro.setId(id);

                        Libro actualizado = libroCommand
                                        .configurar(libro, LibroCommand.TipoOperacion.ACTUALIZAR)
                                        .ejecutar();

                        return ResponseEntity.ok(ApiResponse.<Libro>builder()
                                        .exito(true)
                                        .mensaje("Libro actualizado exitosamente")
                                        .datos(actualizado)
                                        .build());
                } catch (IllegalArgumentException | IllegalStateException e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(ApiResponse.<Libro>builder()
                                                        .exito(false)
                                                        .mensaje(e.getMessage())
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(ApiResponse.<Libro>builder()
                                                        .exito(false)
                                                        .mensaje(e.getMessage())
                                                        .build());
                }
        }
        /**
         * Elimina un libro del catálogo (solo ADMIN).
         * @param id ID del libro a eliminar
         * @param usuarioId ID del usuario que realiza la operación
         * @return ResponseEntity con resultado de la operación
         */

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> eliminar(
                        @PathVariable Integer id,
                        @RequestParam Integer usuarioId) {
                try {
                        Usuario usuario = validadorUsuarios.validarExiste(usuarioId);
                        validadorPermisos.validarGestionLibros(usuario);
                        validadorLibros.validarExiste(id, "eliminar");

                        libroService.eliminar(id);
                        return ResponseEntity.ok(ApiResponse.<Void>builder()
                                        .exito(true)
                                        .mensaje("Libro eliminado exitosamente")
                                        .build());
                } catch (IllegalArgumentException | IllegalStateException e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(ApiResponse.<Void>builder()
                                                        .exito(false)
                                                        .mensaje(e.getMessage())
                                                        .build());
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(ApiResponse.<Void>builder()
                                                        .exito(false)
                                                        .mensaje(e.getMessage())
                                                        .build());
                }
        }
}
