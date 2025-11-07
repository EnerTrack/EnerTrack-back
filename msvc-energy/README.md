
# ⚡ msvc-energy

Microservicio encargado de la **gestión de registros energéticos** y **tipos de energía** dentro del ecosistema **EnerTrack**.  
Desarrollado en **Spring Boot 3** con una **arquitectura limpia por capas**, utilizando buenas prácticas de desacoplamiento, validación, manejo de excepciones y despliegue en **Kubernetes**.

---

## 🚀 Tecnologías y dependencias

| Tecnología | Descripción |
|-------------|--------------|
| **Java 17** | Lenguaje base del microservicio |
| **Spring Boot 3.5.6** | Framework principal para la creación de APIs REST |
| **Spring Data JPA** | Capa de persistencia basada en Hibernate |
| **Spring Validation** | Validación de DTOs de entrada |
| **MapStruct** | Mapeo automático entre entidades y DTOs |
| **Lombok** | Reducción de código repetitivo |
| **Swagger / OpenAPI 3** | Documentación automática de la API |
| **MySQL** | Base de datos relacional |
| **Spring Boot Actuator** | Métricas y monitorización del servicio |
| **Spring Cloud OpenFeign** | Comunicación declarativa entre microservicios |
| **Spring Cloud Config** | Configuración centralizada |
| **Spring Cloud Kubernetes** | Integración nativa con entornos Kubernetes |

---

## 📂 Estructura del Proyecto

```bash
src/
└── main/
    ├── java/
    │   └── dev/
    │       └── ener_track/
    │           └── com/
    │               └── msvc_energy/
    │                   ├── api/
    │                   │   ├── controller/         # Controladores REST
    │                   │   ├── dto/
    │                   │   │   ├── request/        # Objetos de entrada (Request DTOs)
    │                   │   │   ├── response/       # Objetos de salida (Response DTOs)
    │                   │   │   └── errors/         # Respuestas y estructuras de error
    │                   │   └── error_handler/      # Manejadores globales de excepciones
    │                   │
    │                   ├── config/                 # Configuración (Swagger, Beans, Feign, etc.)
    │                   │
    │                   ├── domain/
    │                   │   ├── entities/           # Entidades JPA
    │                   │   └── repositories/       # Repositorios JPA
    │                   │
    │                   ├── infracture/
    │                   │   ├── adstract_service/   # Interfaces de servicios
    │                   │   │   └── generic/        # Servicios genéricos reutilizables
    │                   │   ├── mappers/            # Mappers de MapStruct
    │                   │   └── services/           # Implementaciones de la lógica de negocio
    │                   │
    │                   └── utils/
    │                       ├── enums/              # Enumeraciones del dominio (ej: SortType)
    │                       └── exceptions/         # Excepciones personalizadas
    │
    └── resources/
        ├── application.properties                  # Configuración del microservicio
        └── (otros recursos o configuraciones)
```

## ⚙️ Endpoints principales

## Tipos de Energía

**Base Path:** `/energy-type`

| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET`  | `/energy-type` | Obtiene todos los tipos de energía con paginación y ordenamiento |
| `GET`  | `/energy-type/{name}` | Busca un tipo de energía por nombre |
| `POST` | `/energy-type` | Crea un nuevo tipo de energía |
| `PUT`  | `/energy-type/{id}` | Actualiza la información de un tipo de energía existente |
---


### 🔋 Registros de Energía

**Base Path:** `/energy-record`

| Método | Endpoint | Descripción |
|---------|-----------|-------------|
| `GET`  | `/energy-record` | Obtiene todos los registros de energía paginados y ordenados |
| `GET`  | `/energy-record/{id}` | Obtiene un registro de energía por su ID |
| `POST` | `/energy-record` | Crea un nuevo registro de energía |
| `PUT`  | `/energy-record/{id}` | Actualiza un registro de energía existente por su ID |

#### 📦 Ejemplo de Request (`POST /energy-record`)
🧾 Endpoints disponibles


