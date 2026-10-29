🚀 Proyecto 2 — E-Commerce API
🟢 Sprint 0 — Planeación
1. Nombre del proyecto

Nombre funcional:

E-Commerce API

Nombre técnico:

ecommerce-api
2. Objetivo

Crear una API REST para un pequeño e-commerce, utilizando Java, Spring Boot y Oracle.

La API permitirá:

👤 Registrar usuarios.
🔐 Autenticar usuarios mediante JWT.
📦 Gestionar productos.
🗂️ Gestionar categorías.
🛒 Crear y gestionar carritos.
🧾 Crear pedidos.
📊 Consultar pedidos.
📦 Validar y descontar stock.
👮 Administrar productos mediante roles.
🗄️ Utilizar Oracle como base de datos.
⚙️ Implementar determinadas operaciones mediante SQL y PL/SQL.
3. Tecnologías
Tecnología	Uso
Java 21	Lenguaje principal
Spring Boot	Framework backend
Spring Web	Crear API REST
Spring Data JPA	Persistencia
Hibernate	ORM
Oracle Database	Base de datos
SQL	Consultas y gestión de datos
PL/SQL	Procedimientos y lógica en Oracle
Spring Security	Seguridad
JWT	Autenticación
BCrypt	Cifrado de contraseñas
Bean Validation	Validaciones
Lombok	Reducir código repetitivo
JUnit 5	Testing
Mockito	Mocks para pruebas
Swagger/OpenAPI	Documentación de API
Maven	Gestión del proyecto
Git/GitHub	Control de versiones
Docker	Contenedores
Render	Deploy, si la infraestructura final lo permite

No instalaremos ni configuraremos todo ahora. Cada tecnología entrará en el Sprint correspondiente.

4. Metodología

Trabajaremos de forma iterativa:

Plan
 ↓
Desarrollo
 ↓
Pruebas
 ↓
Correcciones
 ↓
Commit
 ↓
Sprint terminado ✅

Los Sprints serán:

Sprint 0 → Planeación ✅
Sprint 1 → Arquitectura + Oracle ✅
Sprint 2 → Seguridad 
Sprint 3 → Productos + Categorías
Sprint 4 → Carrito + Pedidos
Sprint 5 → Lógica avanzada + PL/SQL
Sprint 6 → Testing
Sprint 7 → Swagger + Documentación
Sprint 8 → Docker + Deploy
5. Arquitectura

Utilizaremos una arquitectura por capas:

             CLIENTE
                │
                ▼
          ┌────────────┐
          │ Controller │
          └──────┬─────┘
                 ▼
             ┌──────┐
             │ DTO  │
             └──┬───┘
                ▼
          ┌────────────┐
          │  Service   │
          └──────┬─────┘
                 ▼
          ┌────────────┐
          │ Repository │
          └──────┬─────┘
                 ▼
          ┌────────────┐
          │JPA/Hibernate│
          └──────┬─────┘
                 ▼
          ┌────────────┐
          │   Oracle   │
          └──────┬─────┘
                 ▼
          ┌────────────┐
          │ SQL/PLSQL  │
          └────────────┘

Esto también nos servirá para entrevistas porque podremos explicar claramente qué responsabilidad tiene cada capa.

6. Estructura inicial

Nuestro paquete principal será:

com.wildev.ecommerce

Y tendremos:

com.wildev.ecommerce
│
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

Importante: no vamos a crear todas las clases desde el comienzo. Las iremos agregando cuando las necesitemos.

7. Modelo de datos

La estructura principal será:

USER
 │
 ├──────────< ORDER
 │              │
 │              └────────< ORDER_ITEM >──── PRODUCT
 │                                              │
 │                                              ▼
 │                                           CATEGORY
 │
 └────────── CART
                │
                └────────< CART_ITEM >──── PRODUCT

En palabras:

Un User puede tener varios Orders.
Un Order tiene varios OrderItems.
Cada OrderItem corresponde a un Product.
Un Product pertenece a una Category.
Un User tiene un Cart.
Un Cart tiene varios CartItems.
Cada CartItem corresponde a un Product.
8. Lo que vamos a aprender

Este proyecto está diseñado para reforzar tus conocimientos de Java Junior + Backend:

Java

OOP
Collections
Exceptions
Optional
Streams
Lambdas

Spring Boot

Controllers
Services
Repositories
Dependency Injection
REST API
DTO
Validaciones
Manejo de errores

Persistencia

JPA
Hibernate
Entities
Relaciones
@OneToMany
@ManyToOne
@OneToOne
Transactions

Oracle

SQL
Sequences
Constraints
Índices
Relaciones
Procedures
Functions
PL/SQL

Seguridad

Spring Security
Authentication
Authorization
BCrypt
JWT
Roles

Calidad y despliegue

JUnit
Mockito
Swagger
Docker
Git/GitHub
Deploy
✅ Resultado del Sprint 0

Al terminar este Sprint tenemos definido:

Proyecto:       E-Commerce API
Nombre técnico: ecommerce-api

Backend:        Java 21 + Spring Boot
Base de datos:  Oracle
Persistencia:   JPA + Hibernate
BD avanzada:    SQL + PL/SQL
Seguridad:      Spring Security + JWT
Testing:        JUnit + Mockito
Documentación:  Swagger/OpenAPI
Contenedores:   Docker
Control:        Git/GitHub