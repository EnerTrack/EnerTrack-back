package dev.ener_track.com.msvc_users.api.dto.request;

import dev.ener_track.com.msvc_users.utils.emuns.Role;
import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "role is required")
    private Role role;
    @NotNull(message = "personId is required")
    private String personId;
    @NotNull(message = "status is required")
    private Status status;
}
