package dev.ener_track.com.msvc_auth.api.controller;

import dev.ener_track.com.msvc_auth.api.dto.request.LoginRequest;
import dev.ener_track.com.msvc_auth.api.dto.response.TokenResponse;
import dev.ener_track.com.msvc_auth.services.adtract_service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Validated @RequestBody LoginRequest request){
        System.out.println("🚀 Entró al /auth/login");
        System.out.println("Request recibido: " + request);
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping("/jwt")
    public ResponseEntity<TokenResponse> validate(@RequestHeader String accessToken){
        TokenResponse token = TokenResponse.builder().
                        AccessToken(accessToken)
                        .build();

        return ResponseEntity.ok(this.authService.ValidateToken(token));
    }

}
