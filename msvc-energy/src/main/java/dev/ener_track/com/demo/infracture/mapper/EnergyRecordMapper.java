package dev.ener_track.com.demo.infracture.mapper;

import dev.ener_track.com.demo.api.dto.request.EnergyRecordRequest;
import dev.ener_track.com.demo.api.dto.response.relationResponse.EnergyRecordRelationResponse;
import dev.ener_track.com.demo.domain.entities.EnergyRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnergyRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "energyType", ignore = true)
    EnergyRecordEntity toEntity(EnergyRecordRequest request);

    @Mapping(target = "energyType.name", source = "energyType.name")
    EnergyRecordRelationResponse toResponse(EnergyRecordEntity entity);

    @Mapping(target = "energyType", ignore = true)
    void updateEnergyRecord(@MappingTarget EnergyRecordEntity entity, EnergyRecordRequest request);
}
