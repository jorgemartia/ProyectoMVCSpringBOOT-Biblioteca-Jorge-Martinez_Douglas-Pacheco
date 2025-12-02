# ProyectoMVCSpringBOOT-Biblioteca-Jorge-Martinez_Douglas-Pacheco

## 📚 Sistema de Gestión de Biblioteca

Sistema web completo para la gestión de préstamos de libros desarrollado con Spring Boot y vanilla JavaScript, implementando patrones de diseño modernos y arquitectura limpia.

## 🎯 Características Principales

- **Gestión de Usuarios**: Registro, login y control de roles (Usuario/Administrador)
- **Catálogo de Libros**: CRUD completo con gestión de disponibilidad
- **Sistema de Préstamos**: Control de préstamos activos con límites y validaciones
- **Panel de Administración**: Gestión completa para administradores
- **Seguridad**: Autenticación con BCrypt y control de permisos
- **Responsive Design**: Interfaz adaptable a diferentes dispositivos

 ## 🛠️ Tecnologías Utilizadas 
Backend

Java 17+
Spring Boot 3.x

Spring Data JPA
Spring Security
Spring Web


MySQL - Base de datos relacional
Lombok - Reducción de código boilerplate
BCrypt - Encriptación de contraseñas

Frontend

HTML5
CSS3 - Con diseño responsive
JavaScript Vanilla - Sin frameworks
Bootstrap 5 - Componentes UI

## 🏗️ Arquitectura y Patrones de Diseño
El proyecto implementa múltiples patrones de diseño para mantener un código limpio, mantenible y escalable:
1. Patrón Command
Encapsula las operaciones del sistema como objetos independientes:

LibroCommand: Gestión de libros (agregar/actualizar)
PrestamoCommand: Creación de préstamos
DevolucionCommand: Proceso de devolución de libros

java// Ejemplo de uso
Libro libro = libroCommand
    .configurar(libro, LibroCommand.TipoOperacion.AGREGAR)
    .ejecutar();
2. Patrón Proxy
Control de acceso y permisos a través de ProxyService:

Verificación de roles de usuario
Control de permisos para operaciones críticas
Validación de acceso a recursos

3. Separación de Responsabilidades

Controllers: Manejo de peticiones HTTP
Services: Lógica de negocio
Repositories: Acceso a datos
Validators: Validaciones modulares
Commands: Operaciones del sistema
DTOs: Transferencia de datos

4. Validadores Modulares
Sistema de validación organizado en componentes especializados:

ValidacionBase: Validaciones genéricas
ValidacionUsuario: Validaciones de usuarios
ValidacionLibro: Validaciones de libros
ValidacionPermiso: Validaciones de permisos
ValidadorPrestamos: Reglas de negocio de préstamos

## 📋 Requisitos Previos

JDK 17 o superior
Maven 3.8+
MySQL 8.0+
IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalación y Configuración
1. Clonar el repositorio
bashgit clone https://github.com/tu-usuario/sistema-biblioteca.git
cd sistema-biblioteca
2. Configurar la base de datos
Crear una base de datos MySQL:
sqlCREATE DATABASE biblioteca;
3. Configurar application.properties
propertiesspring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
4. Compilar y ejecutar
bashmvn clean install
mvn spring-boot:run


La aplicación estará disponible en `http://localhost:8080`

## 👥 Usuarios de Prueba

Al iniciar la aplicación, se crea automáticamente un usuario administrador:

**Administrador:**
- Email: `admin@biblioteca.com`
- Contraseña: `admin123`


## 📁 Estructura del Proyecto

src/main/java/proyecto/biblioteca3/
├── command/           # Patrón Command
│   ├── Command.java
│   ├── LibroCommand.java
│   ├── PrestamoCommand.java
│   └── DevolucionCommand.java
├── config/            # Configuraciones
│   ├── CorsConfig.java
│   ├── SecurityConfig.java
│   └── DataInitializer.java
├── controller/        # Controladores REST
│   ├── LibroController.java
│   ├── PrestamoController.java
│   └── UsuarioController.java
├── dto/              # Objetos de transferencia
│   ├── ApiResponse.java
│   ├── LoginRequest.java
│   └── RegistroRequest.java
├── model/            # Entidades JPA
│   ├── Libro.java
│   ├── Prestamo.java
│   └── Usuario.java
├── repository/       # Repositorios JPA
│   ├── LibroRepository.java
│   ├── PrestamoRepository.java
│   └── UsuarioRepository.java
├── service/          # Lógica de negocio
│   ├── LibroService.java
│   ├── PrestamoService.java
│   ├── ProxyService.java
│   └── UsuarioService.java
└── validador/        # Validadores modulares
    ├── ValidacionBase.java
    ├── ValidacionLibro.java
    ├── ValidacionUsuario.java
    ├── ValidacionPermiso.java
    └── ValidadorPrestamos.java
🔌 API Endpoints
## Usuarios

POST /api/usuarios/registro - Registrar nuevo usuario
POST /api/usuarios/login - Iniciar sesión
GET /api/usuarios - Listar usuarios

## Libros

GET /api/libros - Listar todos los libros
GET /api/libros/{id} - Obtener libro por ID
POST /api/libros - Crear libro (Admin)
PUT /api/libros/{id} - Actualizar libro (Admin)
DELETE /api/libros/{id} - Eliminar libro (Admin)

## Préstamos

GET /api/prestamos - Listar préstamos (Admin: todos, Usuario: propios)
GET /api/prestamos/usuario/{id} - Préstamos de un usuario
POST /api/prestamos - Crear préstamo
PUT /api/prestamos/{id}/devolver - Devolver libro
DELETE /api/prestamos/limpiar-devueltos - Limpiar préstamos devueltos (Admin)

## 💡 Reglas de Negocio
Préstamos

Máximo 3 préstamos activos por usuario
No se puede prestar el mismo libro dos veces simultáneamente
Período de préstamo: 14 días
Solo el usuario propietario o un admin pueden devolver un préstamo

## Libros

Control automático de disponibilidad
Actualización de cantidades al prestar/devolver
Validación de datos completos antes de guardar

## Usuarios

Contraseña mínima de 6 caracteres
Email único en el sistema
Cédula única en el sistema
Rol por defecto: USUARIO

## 🎨 Características del Frontend

Diseño Responsive: Adaptable a móviles, tablets y desktop
SPA (Single Page Application): Navegación sin recargas
Interfaz Intuitiva: Diseño limpio y moderno
Feedback Visual: Alertas y confirmaciones de acciones
Gestión de Estado: Control del usuario actual en memoria

## 🔒 Seguridad

Contraseñas encriptadas con BCrypt
CORS configurado para permitir orígenes específicos
Validación de permisos en cada operación crítica
Control de sesión del lado del cliente
Validación de datos en backend y frontend

## 🧪 Validaciones Implementadas
Usuario

Email único y formato válido
Cédula única
Contraseña mínima de 6 caracteres
Usuario activo para operaciones

Libro

Campos obligatorios completos
ISBN único
Cantidades coherentes (disponible ≤ total)
Disponibilidad para préstamo

Préstamo

Usuario no puede tener préstamo activo del mismo libro
Límite de 3 préstamos activos por usuario
Libro disponible al momento del préstamo
Solo puede devolver el propietario o admin