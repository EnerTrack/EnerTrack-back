package dev.ener_track.com.demo.infracture.mapper;

import dev.ener_track.com.demo.api.dto.request.EnergyTypeRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.domain.entities.EnergyTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnergyTypeMapper {

    @Mapping(target = "id", ignore = true)
    EnergyTypeEntity toEntity(EnergyTypeRequest resquest);

    EnergyTypeResponse toResponse(EnergyTypeEntity entity);

    void updateEnergyTypeEntity(EnergyTypeRequest request,@MappingTarget EnergyTypeEntity entity);
}
