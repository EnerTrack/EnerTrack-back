package dev.ener_track.com.msvc_users.api.dto.request;


import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @Email(message = "email is not valid")
    @NotBlank(message = "email is required")
    private String email;

    @NotNull(message = "phone is required")
    private Long phone;

    @NotBlank(message = "document is required")
    private String document;

    @NotNull(message = "birthDate is required")
    private Date birthDate;

    @NotNull(message = "status is required")
    private Status status;

    @NotBlank(message = "type document is required")
    private String documentType;
}
