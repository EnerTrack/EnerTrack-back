package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.PersonRelationResponse;
import org.apache.coyote.BadRequestException;

public interface ReadByIdService<Id> {
    PersonRelationResponse getById(Id id) throws BadRequestException;
}