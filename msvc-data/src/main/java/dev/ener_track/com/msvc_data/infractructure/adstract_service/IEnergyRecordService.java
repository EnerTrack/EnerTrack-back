package dev.ener_track.com.msvc_data.infractructure.adstract_service;

import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;
import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyTypeMostUseResponse;

import java.util.List;

public interface IEnergyRecordService {

    List<EnergyGroupResponse> getTop5EnergyTypesByCountry();
    List<EnergyTypeMostUseResponse> getAllEnergyTypesUsage();
}
