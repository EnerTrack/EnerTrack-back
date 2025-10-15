package dev.ener_track.com.msvc_users.infrastructure.mappers;


import dev.ener_track.com.msvc_users.api.dto.request.PersonRequest;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.PersonRelationResponse;
import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    PersonEntity toEntity(PersonRequest request);

    @Mapping(target = "documentType", source = "documentType.name")
    PersonRelationResponse toResponse(PersonEntity entity);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    void updatePerson(PersonEntity entity,@MappingTarget PersonRequest request);
}

