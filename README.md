# 🚀 E-Commerce API

API REST para un sistema de comercio electrónico desarrollado con Java 21, Spring Boot y Oracle Database. El proyecto busca aplicar buenas prácticas de desarrollo backend mediante arquitectura por capas, persistencia de datos, seguridad, pruebas automatizadas y despliegue.

El desarrollo se organiza en Sprints incrementales, documentando los avances y manteniendo un historial de cambios mediante Git y GitHub.

## 📌 Estado del proyecto

**Versión:** 0.1.0  
**Estado general:** 🟡 En desarrollo  
**Sprint actual:** Sprint 1 — Arquitectura y conexión con Oracle

### 📊 Progreso de los Sprints

| Sprint | Descripción | Estado |
|---|---|---|
| Sprint 0 | Planeación del proyecto | ✅ Completado |
| Sprint 1 | Arquitectura y conexión con Oracle | 🔄 En progreso |
| Sprint 2 | Seguridad y autenticación JWT | ⬜ Pendiente |
| Sprint 3 | Productos y categorías | ⬜ Pendiente |
| Sprint 4 | Carrito y pedidos | ⬜ Pendiente |
| Sprint 5 | Lógica avanzada y PL/SQL | ⬜ Pendiente |
| Sprint 6 | Pruebas con JUnit y Mockito | ⬜ Pendiente |
| Sprint 7 | Swagger y documentación de la API | ⬜ Pendiente |
| Sprint 8 | Docker y posible despliegue | ⬜ Pendiente |

> El estado de cada Sprint se actualizará cuando sus funcionalidades y criterios de aceptación estén verificados.

## 🎯 Objetivo

Construir una API REST para un pequeño comercio electrónico que permita gestionar usuarios, productos, categorías, carritos de compra y pedidos, con control de acceso y validación del inventario.

## ⚙️ Tecnologías

| Tecnología | Aplicación |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot | Framework backend |
| Spring Web | Desarrollo de endpoints REST |
| Spring Data JPA | Persistencia de datos |
| Hibernate | Mapeo objeto-relacional (ORM) |
| Oracle Database | Base de datos relacional |
| SQL | Consultas y manipulación de datos |
| PL/SQL | Procedimientos, funciones y lógica en Oracle |
| Spring Security | Autenticación y autorización |
| JWT | Autenticación mediante tokens |
| BCrypt | Hash seguro de contraseñas |
| Bean Validation | Validación de datos |
| Lombok | Reducción de código repetitivo |
| JUnit 5 | Pruebas automatizadas |
| Mockito | Simulación de dependencias en pruebas |
| Swagger / OpenAPI | Documentación de endpoints |
| Maven | Gestión de dependencias |
| Git / GitHub | Control de versiones |
| Docker | Contenerización |
| Render | Posible plataforma de despliegue |

Las tecnologías se incorporarán progresivamente durante los Sprints correspondientes.

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura por capas para separar responsabilidades y facilitar el mantenimiento.

```text
Cliente
   |
   v
Controller
   |
   v
DTO y validaciones
   |
   v
Service
   |
   v
Repository
   |
   v
JPA / Hibernate
   |
   v
Oracle Database
```

### Responsabilidades principales

- **Controller:** recibe las solicitudes HTTP y devuelve las respuestas.
- **DTO:** define los datos de entrada y salida de la API.
- **Service:** implementa las reglas de negocio.
- **Repository:** gestiona el acceso a los datos.
- **Entity:** representa las entidades persistentes.
- **Mapper:** transforma entidades y DTO.
- **Security:** gestiona la autenticación y los permisos.
- **Config:** centraliza las configuraciones.
- **Exception:** organiza el manejo de errores.

## 📂 Estructura del proyecto

Paquete principal: `com.wildev.ecommerce`

```text
com.wildev.ecommerce
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
└── service.impl
```

La estructura se ampliará a medida que se implementen las funcionalidades.

## 🗄️ Modelo de datos previsto

Las principales entidades del sistema serán:

- **User:** usuarios, credenciales y roles.
- **Category:** categorías de productos.
- **Product:** información, precios y existencias.
- **Cart:** carrito de compra del usuario.
- **CartItem:** productos y cantidades del carrito.
- **Order:** pedidos realizados.
- **OrderItem:** detalle de los productos de cada pedido.

### Relaciones principales

- Un usuario puede realizar varios pedidos.
- Un usuario tendrá un carrito activo.
- Un carrito contiene varios elementos.
- Un pedido contiene varios elementos.
- Cada elemento de pedido referencia un producto.
- Cada producto pertenece a una categoría.

El modelo definitivo se validará durante la implementación.

## 🔐 Funcionalidades planificadas

- [ ] Registro de usuarios.
- [ ] Inicio de sesión y autenticación JWT.
- [ ] Autorización basada en roles USER y ADMIN.
- [ ] Gestión de productos.
- [ ] Gestión de categorías.
- [ ] Gestión del carrito de compra.
- [ ] Creación y consulta de pedidos.
- [ ] Validación de existencias.
- [ ] Descuento consistente del inventario.
- [ ] Operaciones SQL y PL/SQL.
- [ ] Pruebas unitarias y de integración.
- [ ] Documentación con Swagger / OpenAPI.
- [ ] Contenerización con Docker.
- [ ] Evaluación de alternativas de despliegue.

Las casillas se marcarán únicamente cuando cada funcionalidad esté implementada y validada.

## 🛠️ Metodología de trabajo

Cada Sprint seguirá un proceso incremental:

1. Planificación de tareas.
2. Desarrollo de funcionalidades.
3. Ejecución de pruebas.
4. Corrección de errores.
5. Registro de cambios mediante commits.
6. Actualización del README.
7. Validación de los criterios de aceptación.

## 📅 Historial de desarrollo

### Sprint 0 — Planeación ✅

**Objetivo:** definir las bases técnicas y funcionales del proyecto.

**Resultados:**
- Definición del objetivo y alcance funcional.
- Selección de tecnologías.
- Diseño de la arquitectura por capas.
- Definición de la estructura inicial de paquetes.
- Identificación de las entidades y relaciones principales.
- Planificación de los Sprints de desarrollo.

**Estado:** completado.

### Sprint 1 — Arquitectura y conexión con Oracle 🔄

**Objetivo:** preparar la estructura inicial de Spring Boot y establecer la conexión con Oracle Database.

**Tareas previstas:**
- [ ] Revisar la estructura del proyecto.
- [ ] Configurar las dependencias necesarias.
- [ ] Configurar la conexión con Oracle.
- [ ] Verificar la comunicación entre Spring Boot y la base de datos.
- [ ] Crear un endpoint de prueba.
- [ ] Documentar la configuración necesaria para ejecutar el proyecto.

**Estado:** en progreso. Las tareas se marcarán según los resultados verificados.

## ▶️ Ejecución local

Requisitos previstos:

- Java Development Kit (JDK) 21.
- Maven o Maven Wrapper.
- Oracle Database accesible.
- Git.

### 1. Clonar el repositorio

```bash
git clone https://github.com/WilsonHRamosP/ecommerce-api.git
cd ecommerce-api
```

### 2. Configurar la conexión a Oracle

Configura las propiedades de conexión en `application.properties` o mediante variables de entorno, según la configuración implementada.

No publiques contraseñas ni credenciales reales en el repositorio.

### 3. Ejecutar la aplicación

En Windows, desde la raíz del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

El puerto configurado para el proyecto deberá verificarse en los archivos de configuración. Una vez iniciada la aplicación, los endpoints estarán disponibles según sus rutas implementadas.

## 🧪 Pruebas

El proyecto incorporará pruebas automatizadas para validar las reglas de negocio, la persistencia y el comportamiento de los endpoints.

Herramientas previstas: JUnit 5, Mockito y pruebas de integración cuando corresponda.

## 📚 Objetivos de aprendizaje

- Programación orientada a objetos con Java.
- Collections, Streams, Lambdas y Optional.
- Desarrollo de API REST.
- Inyección de dependencias y arquitectura por capas.
- DTO, validaciones y manejo de errores.
- JPA, Hibernate y relaciones entre entidades.
- Transacciones y consistencia de inventario.
- Oracle SQL, restricciones, índices y PL/SQL.
- Spring Security, JWT, BCrypt y autorización por roles.
- Pruebas automatizadas y documentación técnica.
- Git, GitHub, Docker y despliegue.

## 👨‍💻 Autor

**Wilson Ramos**  
Ingeniero Electrónico | Desarrollador Java Backend Junior

GitHub: [WilsonHRamosP](https://github.com/WilsonHRamosP)

---

*Proyecto de portafolio desarrollado con fines de aprendizaje y práctica de tecnologías backend.*
