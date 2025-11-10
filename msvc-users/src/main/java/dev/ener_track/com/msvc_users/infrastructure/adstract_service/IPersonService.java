package dev.ener_track.com.msvc_users.infrastructure.adstract_service;

import dev.ener_track.com.msvc_users.api.dto.request.PersonRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.PersonRelationResponse;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.CreateService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.ReadAllService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.ReadByIdService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.UpdateService;

public interface IPersonService extends
        ReadAllService<PersonRelationResponse>,
        CreateService<PersonRequest, PersonRelationResponse>,
        UpdateService<PersonRequest, PersonRelationResponse, String>,
        ReadByIdService<String>
{

     ValidateExistence existsByDocument(String document);
    public String FIELD_BY_SORT = "name";

}
