package dev.ener_track.com.msvc_data.api.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CountryEnergyResponse {
    private String country;
    private double generatedMwh;
    private int year;
    private double capacityMwh;
    private double emissionReductionTons;
    private double investmentUsd;
}
