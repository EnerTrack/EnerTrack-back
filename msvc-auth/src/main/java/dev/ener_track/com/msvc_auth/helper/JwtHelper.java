package dev.ener_track.com.msvc_auth.helper;

import dev.ener_track.com.msvc_auth.api.dto.response.VerifyLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtHelper {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(VerifyLogin person){
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + (3600 * 1000));
        return Jwts.builder()
                .setSubject(person.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(this.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token){
        final var expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.after(new Date());
    }

    private Date getExpirationDateFromToken(String token){
        return this.getClaimsByToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsByToken(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(this.singToken(token));
    }

    private Claims singToken(String token){
        return Jwts.parserBuilder().setSigningKey(this.getSecretKey()).build().parseClaimsJws(token).getBody();
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
