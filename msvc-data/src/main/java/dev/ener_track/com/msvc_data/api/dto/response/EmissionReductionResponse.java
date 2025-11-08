package dev.ener_track.com.msvc_data.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmissionReductionResponse {
    private String country;
    private int year;
    private double emissionReductionTons;
    private double previousYearEmission;
    private double percentageChange;
}