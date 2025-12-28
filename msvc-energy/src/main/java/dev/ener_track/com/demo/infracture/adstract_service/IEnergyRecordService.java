package dev.ener_track.com.demo.infracture.adstract_service;

import dev.ener_track.com.demo.api.dto.request.EnergyRecordRequest;
import dev.ener_track.com.demo.api.dto.response.relationResponse.EnergyRecordRelationResponse;
import dev.ener_track.com.demo.infracture.adstract_service.generic.CreateService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.ReadAllService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.ReadByIdService;
import dev.ener_track.com.demo.infracture.adstract_service.generic.UpdateService;

public interface IEnergyRecordService extends
        ReadAllService<EnergyRecordRelationResponse>,
        CreateService<EnergyRecordRequest, EnergyRecordRelationResponse>,
        UpdateService<EnergyRecordRequest, EnergyRecordRelationResponse, String>,
        ReadByIdService<EnergyRecordRelationResponse, String>
{
    public String FIELD_BY_SORT = "name";
}
