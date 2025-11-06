package dev.ener_track.com.msvc_data.infractructure.service;

import dev.ener_track.com.msvc_data.api.dto.response.CountryEnergyResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyTypeMostUseResponse;
import dev.ener_track.com.msvc_data.domain.repositories.EnergyRecordRepository;
import dev.ener_track.com.msvc_data.infractructure.adstract_service.IEnergyRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

}