package dev.ener_track.com.msvc_users.api.dto.response.relationsResponse;

import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.PersonResponse;
import dev.ener_track.com.msvc_users.utils.emuns.Role;
import dev.ener_track.com.msvc_users.utils.emuns.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRelationResponse  {
    private String id;
    private String username;
    private Role role;
    private Status status;
    private PersonRelationResponse person;
}
