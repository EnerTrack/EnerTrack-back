package dev.ener_track.com.msvc_users.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api for the gestion of the energy", version = "1.0", description = "Api Documentation"))
public class OpenApiConfig {
}
