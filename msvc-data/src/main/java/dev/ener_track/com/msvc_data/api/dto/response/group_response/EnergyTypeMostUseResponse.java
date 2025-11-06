package dev.ener_track.com.msvc_data.api.dto.response.group_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnergyTypeMostUseResponse {
    private String energyTypeId;
    private String energyTypeName;
    private Long usageCount;

}
