package dev.ener_track.com.demo.infracture.service;

import dev.ener_track.com.demo.api.dto.request.EnergyTypeRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.demo.domain.entities.EnergyTypeEntity;
import dev.ener_track.com.demo.domain.respositories.EnergyTypeRepository;
import dev.ener_track.com.demo.infracture.adstract_service.IEnergyTypeService;
import dev.ener_track.com.demo.infracture.mapper.EnergyTypeMapper;
import dev.ener_track.com.demo.utils.enums.SortType;
import dev.ener_track.com.demo.utils.exeptions.ErrorMessages;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnergyTypeService implements IEnergyTypeService {

    private EnergyTypeRepository energyTypeRepository;
    private EnergyTypeMapper energyTypeMapper;

    @Override
    public Page<EnergyTypeResponse> getAll(int page, int size, SortType sortType) {

        if (page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.energyTypeRepository.findAll(pagination).map(this.energyTypeMapper::toResponse);
    }

    @Override
    public EnergyTypeResponse create(EnergyTypeRequest request) throws BadRequestException {

        Optional<EnergyTypeEntity> existingEntity = this.energyTypeRepository.findByName(request.getName());

        if (existingEntity.isPresent()) throw new BadRequestException(ErrorMessages.alreadyExists(request.getName()));

        EnergyTypeEntity newEnergyType = this.energyTypeMapper.toEntity(request);
        EnergyTypeEntity savedEntity = this.energyTypeRepository.save(newEnergyType);

        return this.energyTypeMapper.toResponse(savedEntity);
    }

    @Override
    public EnergyTypeResponse update(String id, EnergyTypeRequest request) throws BadRequestException {

        EnergyTypeEntity energyType = this.find(id);

        this.energyTypeMapper.updateEnergyTypeEntity(request, energyType);
        EnergyTypeEntity savedEntity = this.energyTypeRepository.save(energyType);

        return this.energyTypeMapper.toResponse(savedEntity);
    }

    @Override
    public EnergyTypeResponse findByName(String name) {

        Optional<EnergyTypeEntity> existingEntity = this.energyTypeRepository.findByName(name);

        return existingEntity.map(
                entity -> this.energyTypeMapper.toResponse(entity)).orElse(null);

    }

    @Override
    public ValidateExistence existsByName(String name) {
        boolean exists = this.energyTypeRepository.findByName(name).isPresent();
        return new ValidateExistence(exists);
    }

    private EnergyTypeEntity find(String id) throws BadRequestException {
        return this.energyTypeRepository.findById(id).orElseThrow(
                () -> new BadRequestException(ErrorMessages.IdNotFound("Energy Type")));
    }
}
