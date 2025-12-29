package dev.ener_track.com.msvc_data.infractructure.service;

import dev.ener_track.com.msvc_data.api.dto.response.CountryEnergyResponse;
import dev.ener_track.com.msvc_data.api.dto.response.EmissionReductionResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyTypeMostUseResponse;
import dev.ener_track.com.msvc_data.domain.repositories.EnergyRecordRepository;
import dev.ener_track.com.msvc_data.infractructure.adstract_service.IEnergyRecordService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnergyRecordService implements IEnergyRecordService {

        private final EnergyRecordRepository energyRecordRepository;

        @Override
        public List<EnergyGroupResponse> getTop5EnergyTypesByCountry() {
                List<Map<String, Object>> results = energyRecordRepository.findTop5CountriesAndTheirTop5EnergyTypes();

                // Agrupamos por país
                Map<String, List<CountryEnergyResponse>> grouped = results.stream()
                                .collect(Collectors.groupingBy(
                                                row -> (String) row.get("country"),
                                                Collectors.mapping(row -> {
                                                        CountryEnergyResponse response = new CountryEnergyResponse();
                                                        response.setEnergyTypeId((String) row.get("energy_type_id"));
                                                        response.setEnergyTypeName(
                                                                        (String) row.get("energy_type_name"));
                                                        response.setTotalGeneratedMwh(
                                                                        ((Number) row.get("total_generated_mwh"))
                                                                                        .doubleValue());
                                                        return response;
                                                }, Collectors.toList())));

                return grouped.entrySet().stream()
                                .map(entry -> {
                                        EnergyGroupResponse response = new EnergyGroupResponse();
                                        response.setCountry(entry.getKey());
                                        response.setTopEnergyTypes(entry.getValue());
                                        return response;
                                })
                                .toList();
        }

        @Override
        public List<EnergyTypeMostUseResponse> getAllEnergyTypesUsage() {
                return energyRecordRepository.findAllEnergyTypesUsage();
        }

        @Autowired
        public List<EmissionReductionResponse> getEmissionReductionStats() {
                // Contamos cuántos registros tiene el último año
                int total = energyRecordRepository.countByLastYear();
                int limit = 3;
                int offset = total > limit ? new Random().nextInt(total - limit) : 0;

                // Consultamos 3 registros aleatorios del último año con su año anterior
                List<Map<String, Object>> rows = energyRecordRepository
                                .findRandomEmissionRecordsFromLastYearWithPrevious(offset);

                // Convertimos a DTO
                List<EmissionReductionResponse> result = new ArrayList<>();
                for (Map<String, Object> row : rows) {
                        double current = ((Number) row.get("emissionReductionTons")).doubleValue();
                        double previous = row.get("previousYearEmission") != null
                                        ? ((Number) row.get("previousYearEmission")).doubleValue()
                                        : 0.0;

                        double percentageChange = previous == 0 ? 0 : ((current - previous) / previous) * 100;

                        result.add(EmissionReductionResponse.builder()
                                        .country((String) row.get("country"))
                                        .year(((Number) row.get("year")).intValue())
                                        .emissionReductionTons(current)
                                        .previousYearEmission(previous)
                                        .percentageChange(percentageChange)
                                        .build());
                }

                return result;
        }

}