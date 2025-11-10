package dev.ener_track.com.msvc_users.infrastructure.adstract_service;

import dev.ener_track.com.msvc_users.api.dto.request.DocumentTypeRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.DocumentTypeResponse;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.CreateService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.ReadAllService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.UpdateService;

public interface IDocumentTypeService extends
        ReadAllService<DocumentTypeResponse>,
        CreateService<DocumentTypeRequest, DocumentTypeResponse>,
        UpdateService<DocumentTypeRequest, DocumentTypeResponse, String> {

    ValidateExistence existsByName(String name);

    public String FIELD_BY_SORT = "name";
}
