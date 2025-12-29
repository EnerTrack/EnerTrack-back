package dev.ener_track.com.msvc_data.api.controller;

import dev.ener_track.com.msvc_data.api.dto.response.EmissionReductionResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyTypeMostUseResponse;
import dev.ener_track.com.msvc_data.infractructure.adstract_service.IEnergyRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/energy-data")
@AllArgsConstructor
public class EnergyRecordController {

    private final IEnergyRecordService energyRecordService;

    @GetMapping("/top5-countries")
    public ResponseEntity<List<EnergyGroupResponse>> getTop5EnergyTypesByCountry() {
        return ResponseEntity.ok(this.energyRecordService.getTop5EnergyTypesByCountry());
    }

    @GetMapping("/energy-type-usage")
    public ResponseEntity<List<EnergyTypeMostUseResponse>> getAllEnergyTypesUsage() {
        return ResponseEntity.ok(this.energyRecordService.getAllEnergyTypesUsage());
    }

    @GetMapping("/emission-reduction")
    public ResponseEntity<List<EmissionReductionResponse>> getEmissionReductionStats() {
        return ResponseEntity.ok(energyRecordService.getEmissionReductionStats());
    }

}