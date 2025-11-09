# EnerTrack - Backend


EnerTrack es un microservicio backend desarrollado en Java Spring Boot para la gestión de energía, tipos de energía, registros y estadísticas de consumo. Este proyecto forma parte de un ecosistema de monitoreo y análisis energético.
# Estructura del proyecto (dentro de cada microservicio hay mas informacion)
```bash
enertrack-backend/
├── .idea/                  # Configuración de IntelliJ
├── .vscode/                # Configuración de VSCode
├── msvc-data/              # Microservicio de datos energéticos y reducción de emisiones
├── msvc-energy/            # Microservicio principal de energía
├── msvc-gateway/           # API Gateway y orquestación
├── msvc-users/             # Microservicio de usuarios
├── README.md               # Documentación del proyecto
└── docker-compose.yaml     # Orquestación de microservicios con Docker
```
#⚙️ Tecnologías
- Java 17
- Spring Boot
- Spring cloud
- Spring Data JPA
- MySQL
- PosgreSQL
- Docker 
- Swagger
  
#⚙️ Tecnologías a integrar proximanmente
- Kuberentes
- Spring Security

#🚀 Instalación y ejecución
```bash
git clone https://github.com/tu-usuario/enertrack-backend.git
cd enertrack-backend
```
#Levantar microservicios con Docker
```bash
docker-compose up --build
```
Esto levantará todos los microservicios: msvc-data, msvc-energy, msvc-gateway y msvc-users.
## 📡 Endpoints principales

### msvc-users
Url-base ```http://localhost:8090/```

| Método | Endpoint                        | Descripción                     |
|--------|--------------------------------|---------------------------------|
| GET    | /api/users/person               | Listar personas (paginado)      |
| GET    | /api/users/person/{id}          | Obtener persona por ID           |
| POST   | /api/users/person               | Crear persona                    |
| PUT    | /api/users/person/{id}          | Actualizar persona               |
| GET    | /api/users/document-type        | Listar tipos de documentos       |
| POST   | /api/users/document-type        | Crear tipo de documento          |
| PUT    | /api/users/document-type/{id}   | Actualizar tipo de documento     |

---

### msvc-energy

| Método | Endpoint                        | Descripción                     |
|--------|--------------------------------|---------------------------------|
| GET    | /api/energy/energy-record       | Listar registros de energía     |
| GET    | /api/energy/energy-record/{id}  | Obtener registro de energía por ID |
| POST   | /api/energy/energy-record       | Crear registro de energía       |
| PUT    | /api/energy/energy-record/{id}  | Actualizar registro de energía |
| GET    | /api/energy/energy-type         | Listar tipos de energía         |
| GET    | /api/energy/energy-type/{name}  | Obtener tipo de energía por nombre |
| POST   | /api/energy/energy-type         | Crear tipo de energía           |
| PUT    | /api/energy/energy-type/{id}    | Actualizar tipo de energía     |

---

### msvc-data

| Método | Endpoint                             | Descripción                                |
|--------|-------------------------------------|--------------------------------------------|
| GET    | /api/data/energy-data/top5-countries | Obtener top 5 paises con tipos de energía   |
| GET    | /api/data/energy-data/energy-type-usage | Obtener uso de todos los tipos de energía |
| GET    | /api/data/energy-data/emission-reduction | Obtener estadísticas de reducción de emisiones |

