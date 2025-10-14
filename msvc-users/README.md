# 🧩 msvc-users

Microservicio encargado de la **gestión de usuarios** y **tipos de documentos** dentro del ecosistema **EnerTrack**.  
Desarrollado en **Spring Boot 3** siguiendo una arquitectura limpia basada en capas, con buenas prácticas de desacoplamiento y manejo de excepciones.

---

## 🚀 Tecnologías y dependencias

| Tecnología | Descripción |
|-------------|--------------|
| **Java 17** | Lenguaje base del microservicio |
| **Spring Boot 3.5.6** | Framework principal para la creación de APIs REST |
| **Spring Data JPA** | Capa de persistencia basada en Hibernate |
| **Spring Validation** | Validación de DTOs y requests |
| **MapStruct** | Mapeo automático entre entidades y DTOs |
| **Lombok** | Reducción de código repetitivo |
| **Swagger / OpenAPI 3** | Documentación automática de la API |
| **PostgreSQL** | Base de datos relacional |
| **Spring Boot Actuator** | Monitoreo y métricas |
| **Spring Cloud Config** | Configuración centralizada (opcional) |

---

## 🧱 Estructura del proyecto
## 📂 Estructura del Proyecto

```bash
src/
└── main/
    ├── java/
    │   └── dev/
    │       └── ener_track/
    │           └── com/
    │               └── msvc_users/
    │                   ├── api/
    │                   │   ├── controller/           # Controladores REST
    │                   │   ├── dto/
    │                   │   │   ├── errors/           # Respuestas de error
    │                   │   │   ├── request/          # Objetos de entrada (Request DTOs)
    │                   │   │   └── response/         # Objetos de salida (Response DTOs)
    │                   │   └── error_handler/        # Manejadores globales de errores
    │                   │
    │                   ├── config/                   # Configuración general (Swagger, beans, etc.)
    │                   │
    │                   ├── domain/
    │                   │   ├── entities/             # Entidades JPA
    │                   │   └── repositories/         # Interfaces de repositorios JPA
    │                   │
    │                   ├── infrastructure/
    │                   │   ├── adstract_service/     # Interfaces de capa de servicio
    │                   │   │   └── generic/          # Servicios genéricos comunes
    │                   │   ├── mappers/              # Mappers entre entidades y DTOs (MapStruct)
    │                   │   └── services/             # Implementaciones de lógica de negocio
    │                   │
    │                   └── utils/
    │                       ├── emuns/                # Enumeraciones del dominio (ej: Status)
    │                       └── exeptions/            # Excepciones personalizadas
    │
    └── resources/
        ├── application.properties                    # Configuración principal del microservicio
        └── (otros recursos estáticos o de configuración)
```

## ⚙️ Endpoints principales

### 📄 Tipos de Documentos
**Base Path:** `/document-type`

| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `POST` | `/document-type` | Registrar un nuevo tipo de documento |
| `GET`  | `/document-type` | Listar todos los tipos de documentos |
| `PUT`  | `/document-type/{id}` | Actualizar estatus del los tipos de documento por ID |

---

### 👤 Usuarios
**Base Path:** `/person`

| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `POST` | `/person` | Registrar un nuevo usuario |
| `GET`  | `/person` | Obtener todos los usuarios |
| `PUT`  | `/person/{id}` | Actualizar usuario por ID |

---
