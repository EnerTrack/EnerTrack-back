package dev.ener_track.com.demo.api.dto.response.relationResponse;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.api.dto.response.basicResponse.PersonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyRecordRelationResponse {

    private String id;
    private String country;
    private int year;
    private double generatedMwh;
    private double capacityMwh;
    private double emissionReductionTons;
    private double investmentUsd;
    private double source;
    private LocalDateTime createdAt;
    private EnergyTypeResponse energyType;
    private PersonResponse person;
}

