package dev.ener_track.com.msvc_data.api.dto.response.group_response;

import dev.ener_track.com.msvc_data.api.dto.response.CountryEnergyResponse;
import dev.ener_track.com.msvc_data.api.dto.response.EnergyTypeResponse;
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
    private EnergyTypeResponse energyTypeId;
    private String energyTypeName;
    private List<CountryEnergyResponse> topCountries;}
