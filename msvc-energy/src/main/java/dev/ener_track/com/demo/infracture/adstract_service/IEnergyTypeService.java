package dev.ener_track.com.demo.infracture.adstract_service;

import dev.ener_track.com.demo.api.dto.request.EnergyTypeRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.infracture.adstract_service.generic.CreateService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.ReadAllService;

public interface IEnergyTypeService extends
        CreateService<EnergyTypeRequest, EnergyTypeResponse>,
        ReadAllService<EnergyTypeResponse> {

    public String FIELD_BY_SORT = "name";

}
