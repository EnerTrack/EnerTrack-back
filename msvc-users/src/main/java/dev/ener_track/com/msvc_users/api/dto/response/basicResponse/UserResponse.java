package dev.ener_track.com.msvc_users.api.dto.response.basicResponse;

import dev.ener_track.com.msvc_users.utils.emuns.Role;
import dev.ener_track.com.msvc_users.utils.emuns.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private Role role;
    private Status status;
}
