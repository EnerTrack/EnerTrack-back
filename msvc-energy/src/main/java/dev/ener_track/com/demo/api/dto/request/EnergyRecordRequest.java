package dev.ener_track.com.demo.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyRecordRequest {

    @NotBlank(message = "Country name is required.")
    @Size(max = 100, message = "Country name cannot exceed 100 characters.")
    private String country;

    @Min(value = 1900, message = "Year must not be earlier than 1900.")
    @Max(value = 2100, message = "Year must not be later than 2100.")
    private int year;

    @Positive(message = "Generated MWh must be greater than 0.")
    private double generatedMwh;

    @Positive(message = "Capacity MWh must be greater than 0.")
    private double capacityMwh;

    @PositiveOrZero(message = "Emission reduction (tons) must be zero or a positive value.")
    private double emissionReductionTons;

    @PositiveOrZero(message = "Investment (USD) must be zero or a positive value.")
    private double investmentUsd;

    @NotNull(message = "Energy source value is required.")
    private Double source;

    @NotBlank(message = "User ID is required.")
    private String userId;

    @NotBlank(message = "Energy type name is required.")
    private String energyTypeName;
}
