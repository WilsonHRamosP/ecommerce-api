================================================================================
AVANCE DEFINITIVO - PROYECTO E-COMMERCE API
Última actualización: 28 de octubre de 2026 (sesión de debug de seguridad)
================================================================================

1. RESUMEN GENERAL
--------------------------------------------------------------------------------
Proyecto: API REST para sistema de comercio electrónico
Tecnologías: Java 21, Spring Boot 3.5.13, Oracle Database, Spring Security, JWT
Estado: En desarrollo activo
Sprints completados: 0, 1, 2 y 3
Progreso estimado: ~40% (estimación del proyecto)

Implementado:
- Arquitectura base con Spring Boot y conexión a Oracle.
- Registro e inicio de sesión con autenticación JWT.
- Spring Security con autorización por roles USER y ADMIN.
- CRUD de categorías con control de acceso.
- Manejo centralizado de excepciones.

Siguiente trabajo: Sprint 4 - Productos.

2. TECNOLOGÍAS Y CONFIGURACIÓN
--------------------------------------------------------------------------------
- Java 21
- Spring Boot 3.5.13
- Maven mediante Maven Wrapper (mvnw)
- Oracle Database, driver ojdbc11
- URL de conexión: jdbc:oracle:thin:@//localhost:1521/FREEPDB1
- Hibernate/JPA: ddl-auto=update
- Puerto de la aplicación: 8086
- Spring Security y JWT mediante jjwt 0.13.0
- BCrypt para el hash de contraseñas

Dependencias principales:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-security
- jjwt-api, jjwt-impl y jjwt-jackson (0.13.0)
- ojdbc11
- Lombok (opcional)
- Spring Boot DevTools (opcional)
- Thymeleaf (conservado de la configuración original)
- spring-boot-starter-test

Variables de entorno:
- DB_USERNAME: usuario de Oracle
- DB_PASSWORD: contraseña de Oracle
- JWT_SECRET: secreto Base64 para firmar los tokens JWT
- APP_ADMIN_EMAIL: email del administrador, opcional
- APP_ADMIN_PASSWORD: contraseña del administrador, opcional

Nota: verificar en application.properties que los nombres de las propiedades JWT
coincidan con los usados por JwtService. El reporte no confirma el contenido
actual de esos archivos de configuración.

3. ARQUITECTURA
--------------------------------------------------------------------------------
Capas:
- Entity: modelos de dominio
- Repository: acceso a datos con Spring Data JPA
- Service: interfaces y lógica de negocio
- Controller: endpoints REST
- DTO: objetos de solicitud y respuesta
- Security: configuración de seguridad, filtro JWT y servicio JWT
- Exception: manejo centralizado de errores

4. ENTIDADES IMPLEMENTADAS
--------------------------------------------------------------------------------
User (tabla APP_USERS; secuencia APP_USERS_SEQ):
- id: Long
- fullName: String, obligatorio, máximo 100
- email: String, obligatorio, único, máximo 150
- passwordHash: String, obligatorio, máximo 100
- role: enum Role (USER / ADMIN)
- enabled: boolean, por defecto true
- Validaciones declaradas: @NotBlank, @Email y @Size

Category (tabla CATEGORIES; secuencia CATEGORY_SEQ):
- id: Long
- name: String, obligatorio, único, máximo 100
- description: String, opcional, máximo 500
- Validaciones declaradas: @NotBlank y @Size

Role:
- Enum con valores USER y ADMIN.

5. REPOSITORIOS IMPLEMENTADOS
--------------------------------------------------------------------------------
UserRepository (JpaRepository<User, Long>):
- findByEmailIgnoreCase(email)
- existsByEmailIgnoreCase(email)

CategoryRepository (JpaRepository<Category, Long>):
- findByNameIgnoreCase(name)
- existsByNameIgnoreCase(name)

6. SERVICIOS IMPLEMENTADOS
--------------------------------------------------------------------------------
AuthService:
- register(RegisterRequest): valida que el email no exista, hashea la contraseña
  con BCrypt, asigna USER por defecto y devuelve UserResponse.
- login(LoginRequest): autentica mediante AuthenticationManager y devuelve
  AuthResponse con el token JWT y el tipo Bearer.

CategoryService / CategoryServiceImpl:
- findAll()
- findById(id), lanza ResourceNotFoundException si no existe
- create(category), valida nombre duplicado
- update(id, category), valida unicidad del nombre
- delete(id)

CustomUserDetailsService:
- Implementa UserDetailsService.
- loadUserByUsername(email): carga el usuario por email y construye UserDetails
  con sus roles.

7. CONTROLADORES Y ENDPOINTS IMPLEMENTADOS
--------------------------------------------------------------------------------
AuthController:
- POST /api/auth/register -> registro de usuario (201 Created)
- POST /api/auth/login    -> inicio de sesión y token JWT

CategoryController (/api/categories):
- GET    /api/categories      -> lista categorías
- GET    /api/categories/{id} -> obtiene categoría por ID
- POST   /api/categories      -> crea categoría (201 Created)
- PUT    /api/categories/{id} -> actualiza categoría
- DELETE /api/categories/{id} -> elimina categoría (204 No Content)

HealthController:
- GET /api/health -> estado del servidor

8. DTO IMPLEMENTADOS
--------------------------------------------------------------------------------
- RegisterRequest: fullName, email y password, con validaciones
- LoginRequest: email y password, con validaciones
- AuthResponse: accessToken y tokenType
- UserResponse: id, fullName, email y role

9. SEGURIDAD JWT
--------------------------------------------------------------------------------
SecurityConfig:
- Beans: PasswordEncoder (BCrypt), AuthenticationProvider,
  AuthenticationManager y SecurityFilterChain.
- Sesiones stateless; CSRF deshabilitado; formLogin y HTTP Basic deshabilitados.
- Rutas públicas: GET /api/health, POST /api/auth/register y
  POST /api/auth/login.
- USER y ADMIN: GET /api/categories/**
- ADMIN: POST, PUT y DELETE /api/categories/**
- Las demás rutas requieren autenticación.

JwtService:
- generateToken(userDetails)
- extractUsername(token)
- isTokenValid(token, userDetails)
- Firma HMAC y expiración configurada mediante propiedades de la aplicación
  según el código existente; confirmar sus nombres en application.properties.

JwtAuthenticationFilter:
- Extiende OncePerRequestFilter.
- Extrae el Bearer token del header Authorization, lo valida y establece
  Authentication en SecurityContextHolder.

Parámetros reportados:
- Expiración del token: 3.600.000 ms (1 hora).
- El enum contiene USER y ADMIN; Spring Security debe mapearlos correctamente
  a las autoridades usadas por las reglas de autorización.
- El filtro JWT maneja excepciones silenciosamente, según el reporte original.

10. MANEJO DE EXCEPCIONES
--------------------------------------------------------------------------------
- ResourceNotFoundException: excepción personalizada para recursos inexistentes.
- GlobalExceptionHandler (@RestControllerAdvice):
    - ResourceNotFoundException -> 404 Not Found
    - IllegalArgumentException -> 400 Bad Request
      Las respuestas incluyen un mensaje JSON, según la implementación descrita.

11. MODELO DE DATOS IMPLEMENTADO
--------------------------------------------------------------------------------
APP_USERS:
- ID: BIGINT, clave primaria, secuencia
- FULL_NAME: VARCHAR2(100), NOT NULL
- EMAIL: VARCHAR2(150), NOT NULL, UNIQUE
- PASSWORD_HASH: VARCHAR2(100), NOT NULL
- USER_ROLE: VARCHAR2(20), NOT NULL
- IS_ENABLED: NUMBER, NOT NULL, valor por defecto reportado como true

CATEGORIES:
- ID: BIGINT, clave primaria, secuencia
- NAME: VARCHAR2(100), NOT NULL, UNIQUE
- DESCRIPTION: VARCHAR2(500), nullable

Nota: los tipos físicos finales dependen de la configuración de JPA/Hibernate
y de la base de datos. La descripción anterior refleja el modelo reportado.

12. PRUEBAS EXISTENTES
--------------------------------------------------------------------------------
- CategoryRepositoryTest: pruebas del repositorio de categorías
- EcommerceApiApplicationTests: prueba base de Spring Boot
- DemoApplicationTests: prueba base de Spring Boot

Las pruebas unitarias completas con JUnit/Mockito y las pruebas de integración
se mantienen como trabajo pendiente.

13. ESTADO DE SPRINTS
--------------------------------------------------------------------------------
Sprint 0 - Planeación: COMPLETADO
- Objetivo, alcance, tecnologías, arquitectura y planificación.

Sprint 1 - Arquitectura y Oracle: COMPLETADO
- Estructura Spring Boot, dependencias, conexión a Oracle, endpoint de salud
  y documentación SETUP_MAVEN.md.

Sprint 2 - Seguridad y JWT: COMPLETADO
- Spring Security, JWT, registro/login, autorización por roles y filtro JWT.

Sprint 3 - Categorías: COMPLETADO
- Entidad Category, CRUD, unicidad del nombre, control de acceso y excepciones.

Sprint 4 - Productos: PENDIENTE (siguiente)
- Crear entidad Product con id, name, description, price, stock y relación
  ManyToOne con Category.
- Crear ProductRepository.
- Crear ProductService y ProductServiceImpl.
- Crear ProductController con CRUD.
- Crear DTOs CreateProductRequest, UpdateProductRequest y ProductResponse.
- Configurar permisos en SecurityConfig.
- Validar precio, stock, categoría y datos de entrada.

Sprint 5 - Carrito y pedidos: PENDIENTE
- Entidades Cart, CartItem, Order y OrderItem.
- Relaciones entre entidades.
- Validación de inventario y descuento de stock al crear un pedido.

Sprint 6 - Lógica avanzada y PL/SQL: PENDIENTE
- Procedimientos almacenados y funciones PL/SQL para operaciones complejas
  y gestión de pedidos.

Sprint 7 - Pruebas con JUnit y Mockito: PENDIENTE
- Pruebas unitarias, mocks, pruebas de integración y cobertura.

Sprint 8 - Swagger y documentación: PENDIENTE
- Añadir SpringDoc OpenAPI y documentar los endpoints.
- Configurar la interfaz Swagger UI, normalmente en /swagger-ui/index.html
  con SpringDoc, según la versión y configuración utilizada.

Sprint 9 - Docker y despliegue: PENDIENTE
- Dockerfile, estrategia de contenedores y despliegue.
- Revisar la estrategia de base de datos antes de incluir Oracle en
  docker-compose; la imagen y licencia deben ser compatibles con el entorno.

14. NOTAS DE CONTINUIDAD
--------------------------------------------------------------------------------
- Puerto: 8086.
- Oracle: servicio FREEPDB1.
- No almacenar credenciales ni JWT_SECRET directamente en el código.
- ddl-auto=update está configurado según el reporte; revisar su conveniencia
  antes de producción.
- Los usuarios se registran con rol USER por defecto.
- El email se normaliza a minúsculas al registrar.
- La validación de nombres de categorías es case-insensitive según el reporte.
- Antes de avanzar, comprobar que la aplicación compila y que los tests actuales
  pasan.

15. SESIÓN DEL 28 DE OCTUBRE DE 2026 - DEBUG DE SEGURIDAD
--------------------------------------------------------------------------------
Problema resuelto: Usuario con rol ROLE_USER recibía 401 (Unauthorized) en lugar
de 403 (Forbidden) al intentar crear una categoría (POST /api/categories).

Causa: En modo STATELESS, Spring Security no diferenciaba correctamente entre
errores de autenticación y errores de autorización.

Solución aplicada en SecurityConfig.java:
- Se agregaron beans personalizados AuthenticationEntryPoint y AccessDeniedHandler
  con logging detallado y respuestas JSON.
- AuthenticationEntryPoint devuelve 401 con JSON cuando no hay autenticación.
- AccessDeniedHandler devuelve 403 con JSON cuando el usuario no tiene permisos.
- Ambos handlers incluyen logs en consola para facilitar el debug futuro.

Cambios en JwtAuthenticationFilter.java:
- Se agregó logging detallado del proceso de validación JWT (email, roles,
  estado del usuario, validez del token).
- Se corrigió el catch para no limpiar el SecurityContext si ya existe
  autenticación, evitando que peticiones parcialmente autenticadas pierdan
  el contexto.

Cambios en JwtService.java:
- Se agregó logging detallado en isTokenValid() que muestra subject del token,
  username de UserDetails, comparación, fecha de expiración y resultado final.

Verificación: Compilación exitosa con Maven, prueba en Postman confirmó 403
correcto para usuario sin permisos.

Siguiente acción recomendada:
Comenzar Sprint 4 - Productos, siguiendo la estructura actual del proyecto
y revisando primero los archivos reales de Category y CategoryServiceImpl
para mantener consistencia con el código existente.

================================================================================
FIN DEL REPORTE DEFINITIVO
================================================================================