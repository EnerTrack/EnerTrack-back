package dev.ener_track.com.msvc_data.infractructure.adstract_service;

import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyGroupResponse;

import java.util.List;

public interface IService {

    List<EnergyGroupResponse> getTop5CountriesByEnergyType();
}
