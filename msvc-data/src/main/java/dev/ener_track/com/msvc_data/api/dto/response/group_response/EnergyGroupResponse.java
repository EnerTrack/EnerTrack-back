package dev.ener_track.com.msvc_data.api.dto.response.group_response;

import dev.ener_track.com.msvc_data.api.dto.response.CountryEnergyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnergyGroupResponse {
    private String country;
    private List<CountryEnergyResponse> topEnergyTypes;
}
