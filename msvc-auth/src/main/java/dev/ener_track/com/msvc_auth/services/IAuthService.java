package dev.ener_track.com.msvc_auth.services;

import dev.ener_track.com.msvc_auth.api.dto.request.LoginRequest;
import dev.ener_track.com.msvc_auth.api.dto.response.TokenResponse;

public interface IAuthService {

    TokenResponse login(LoginRequest request);
    public TokenResponse ValidateToken(TokenResponse token);
}
