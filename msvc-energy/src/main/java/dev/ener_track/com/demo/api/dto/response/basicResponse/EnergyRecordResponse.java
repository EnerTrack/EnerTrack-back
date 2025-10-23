package dev.ener_track.com.demo.api.dto.response.basicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyRecordResponse {

    private String id;
    private String country;
    private int year;
    private double generatedMwh;
    private double capacityMwh;
    private double emissionReductionTons;
    private double investmentUsd;
    private double source;
    private String energyTypeId;
    private LocalDateTime createdAt;

}

