package dev.ener_track.com.demo.infracture.adstract_service;

import dev.ener_track.com.demo.api.dto.request.EnergyTypeRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.demo.infracture.adstract_service.generic.CreateService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.ReadAllService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.UpdateService;

public interface IEnergyTypeService extends
        CreateService<EnergyTypeRequest, EnergyTypeResponse>,
        ReadAllService<EnergyTypeResponse>,
        UpdateService<EnergyTypeRequest, EnergyTypeResponse, String> {

    EnergyTypeResponse findByName(String name);
    ValidateExistence existsByName(String name);

    public String FIELD_BY_SORT = "name";

}
