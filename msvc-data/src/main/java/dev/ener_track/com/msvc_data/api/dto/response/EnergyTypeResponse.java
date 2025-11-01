package dev.ener_track.com.msvc_data.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyTypeResponse {

    private String id;
    private String name;
    private String status;
}
