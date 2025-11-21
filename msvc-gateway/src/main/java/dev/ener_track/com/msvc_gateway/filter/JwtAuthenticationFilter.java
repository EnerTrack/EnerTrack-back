package dev.ener_track.com.msvc_gateway.filter;

import dev.ener_track.com.msvc_gateway.helper.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String method = request.getMethod().name();

        // Permitir GET en /api/energy/** y /api/data/** sin token
        if ("GET".equals(method) && 
            (path.startsWith("/api/energy/") || path.startsWith("/api/data/"))) {
            log.debug("Permitiendo acceso sin token para GET: {}", path);
            return chain.filter(exchange);
        }

        // Permitir acceso al endpoint de autenticación
        if (path.startsWith("/api/auth/") || path.startsWith("/auth/")) {
            log.debug("Permitiendo acceso sin token para: {}", path);
            return chain.filter(exchange);
        }

        // Obtener el token del header
        String authHeader = request.getHeaders().getFirst("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Token no encontrado en la petición: {}", path);
            return onError(exchange, "Token no proporcionado", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7); // Remover "Bearer "

        // Validar el token
        if (!jwtHelper.validateToken(token)) {
            log.warn("Token inválido para la petición: {}", path);
            return onError(exchange, "Token inválido o expirado", HttpStatus.UNAUTHORIZED);
        }

        log.debug("Token válido para la petición: {}", path);
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().add("Content-Type", "application/json");
        
        String body = String.format("{\"error\": \"%s\", \"status\": %d}", message, status.value());
        return response.writeWith(
            Mono.just(response.bufferFactory().wrap(body.getBytes()))
        );
    }

    @Override
    public int getOrder() {
        return -100; // Ejecutar antes que otros filtros
    }
}



