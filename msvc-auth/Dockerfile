# ============================================
# 🔹 ETAPA 1: Construcción del JAR
# ============================================
FROM eclipse-temurin:17-jdk-alpine AS builder

# Creamos el directorio de trabajo
WORKDIR /app

# Copiamos el pom.xml del microservicio
COPY ./pom.xml .

# Copiamos el wrapper de Maven y su configuración
COPY ./.mvn ./.mvn
COPY ./mvnw .

# Descargamos dependencias sin compilar
RUN ./mvnw dependency:go-offline -B

# Copiamos el código fuente
COPY ./src ./src

# Compilamos el proyecto (sin tests)
RUN ./mvnw clean package -DskipTests

# ============================================
# 🔹 ETAPA 2: Imagen final
# ============================================
FROM eclipse-temurin:17-jre-alpine

# Directorio de ejecución
WORKDIR /app

# Copiamos el JAR generado desde la etapa builder
COPY --from=builder /app/target/msvc-auth-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8004

# Ejecutamos la app
CMD ["java", "-jar", "app.jar"]
