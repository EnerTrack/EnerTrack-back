package dev.ener_track.com.msvc_auth.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class VerifyEmail {

    @NotNull(message = "Email is required")
    private String email;
}
