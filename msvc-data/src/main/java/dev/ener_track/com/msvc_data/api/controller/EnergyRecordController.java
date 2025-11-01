package dev.ener_track.com.msvc_data.api.controller;

import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.infractructure.service.EnergyRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/energy-data")
@RequiredArgsConstructor
public class EnergyRecordController {

    private final EnergyRecordService energyRecordService;

    @GetMapping("/top5-countries")
    public ResponseEntity<List<EnergyGroupResponse>> getTop5CountriesByEnergyType() {
        return ResponseEntity.ok(energyRecordService.getTop5CountriesByEnergyType());
    }
}