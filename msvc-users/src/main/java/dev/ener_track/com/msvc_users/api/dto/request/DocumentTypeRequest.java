package dev.ener_track.com.msvc_users.api.dto.request;

import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "status is required")
    private Status status;
}
