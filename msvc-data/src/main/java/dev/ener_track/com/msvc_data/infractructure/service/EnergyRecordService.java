package dev.ener_track.com.msvc_data.infractructure.service;


import dev.ener_track.com.msvc_data.api.dto.response.CountryEnergyResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.domain.entities.EnergyRecordEntity;
import dev.ener_track.com.msvc_data.domain.repositories.EnergyRecordRepository;
import dev.ener_track.com.msvc_data.infractructure.adstract_service.IService;
import dev.ener_track.com.msvc_data.infractructure.client.EnergyFeing;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnergyRecordService implements IService {

    private final EnergyRecordRepository energyRecordRepository;
    private final EnergyFeing energyFeing;

    @Override
    public List<EnergyGroupResponse> getTop5CountriesByEnergyType() {
        // 1️⃣ Consultar datos (nativo: cada fila viene como Map<String, Object>)
        List<Map<String, Object>> records = energyRecordRepository.findTop5CountriesByEnergyType();

        // 2️⃣ Agrupar por nombre del tipo de energía
        Map<String, List<Map<String, Object>>> groupedByEnergyType = records.stream()
                .collect(Collectors.groupingBy(r -> (String) r.get("energy_type_name")));

        // 3️⃣ Mapear a la respuesta final
        return groupedByEnergyType.entrySet().stream()
                .map(entry -> EnergyGroupResponse.builder()
                        .energyTypeName(entry.getKey()) // nombre de la energía
                        .topCountries(entry.getValue().stream()
                                .map(r -> CountryEnergyResponse.builder()
                                        .country((String) r.get("country"))
                                        .generatedMwh(Double.parseDouble(r.get("generated_mwh").toString()))
                                        .year((Integer) r.get("year"))
                                        .capacityMwh(Double.parseDouble(r.get("capacity_mwh").toString()))
                                        .emissionReductionTons(Double.parseDouble(r.get("emission_reduction_tons").toString()))
                                        .investmentUsd(Double.parseDouble(r.get("investment_usd").toString()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }


}