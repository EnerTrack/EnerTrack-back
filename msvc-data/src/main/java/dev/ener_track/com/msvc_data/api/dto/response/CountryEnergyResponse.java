package dev.ener_track.com.msvc_data.api.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CountryEnergyResponse {
    private String energyTypeId;
    private String energyTypeName;
    private double totalGeneratedMwh;
}
