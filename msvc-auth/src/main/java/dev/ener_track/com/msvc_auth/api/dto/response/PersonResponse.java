package dev.ener_track.com.msvc_auth.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Long phone;
    private String document;
    private Date birthDate;
}
