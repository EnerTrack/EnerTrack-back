package dev.ener_track.com.demo.infracture.adstract_service.generic;

import dev.ener_track.com.demo.api.dto.response.relationResponse.EnergyRecordRelationResponse;
import org.apache.coyote.BadRequestException;

public interface ReadByIdService<Response, Id> {
    EnergyRecordRelationResponse getById(Id id) throws BadRequestException;
}