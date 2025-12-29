package dev.ener_track.com.msvc_auth.services;
import dev.ener_track.com.msvc_auth.api.dto.request.LoginRequest;
import dev.ener_track.com.msvc_auth.api.dto.request.VerifyEmail;
import dev.ener_track.com.msvc_auth.api.dto.response.TokenResponse;
import dev.ener_track.com.msvc_auth.api.dto.response.VerifyLogin;
import dev.ener_track.com.msvc_auth.client.UserFeing;
import dev.ener_track.com.msvc_auth.helper.JwtHelper;
import dev.ener_track.com.msvc_auth.services.adtract_service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final UserFeing userFeing;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private static final String USER_EXCEPTION_MSG = "User not authorized";

    @Override
    public TokenResponse login(LoginRequest request) {
        System.out.println("🟡 Entró a AuthService.login()");
        VerifyEmail verifyEmail = new VerifyEmail(request.getEmail());
        VerifyLogin verify = this.userFeing.validateLogin(verifyEmail).getBody();
        System.out.println("🟢 Respuesta Feign: " + verify);

        this.validPassword(verify, request);
        System.out.println("✅ Password correcto");

        return TokenResponse.builder()
                .AccessToken(jwtHelper.generateToken(verify))
                .username(verify.getName())
                .build();
    }


    @Override
    public TokenResponse ValidateToken(TokenResponse token) {
        if(this.jwtHelper.validateToken(token.getAccessToken())) {
           return TokenResponse.builder()
                   .AccessToken(token.getAccessToken())
                   .build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
    }

    private void validPassword(VerifyLogin verifyLogin, LoginRequest login){
        if (!passwordEncoder.matches(login.getPassword(), verifyLogin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }
        System.out.println("✅ Hicieron match");
    }

}
