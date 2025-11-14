package dev.ener_track.com.msvc_users.infrastructure.mappers;

import dev.ener_track.com.msvc_users.api.dto.request.UserRequest;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.UserRelationResponse;
import dev.ener_track.com.msvc_users.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserRequest request);

    UserRelationResponse toResponse(UserEntity entity);

    @Mapping(target = "personId", ignore = true)
    void updateUser(@MappingTarget UserRequest request, UserEntity entity);
}

