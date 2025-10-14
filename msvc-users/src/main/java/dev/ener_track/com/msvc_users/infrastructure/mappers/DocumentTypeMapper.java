package dev.ener_track.com.msvc_users.infrastructure.mappers;

import dev.ener_track.com.msvc_users.api.dto.request.DocumentTypeRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.DocumentTypeResponse;
import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    @Mapping(target = "id", ignore = true)
    DocumentTypeEntity toEntity(DocumentTypeRequest request);

    DocumentTypeResponse toResponse(DocumentTypeEntity entity);

    void updateDocumentType(DocumentTypeEntity entity,@MappingTarget DocumentTypeRequest request);
}
