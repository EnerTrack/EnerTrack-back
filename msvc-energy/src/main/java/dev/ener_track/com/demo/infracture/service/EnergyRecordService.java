package dev.ener_track.com.demo.infracture.service;

import dev.ener_track.com.demo.api.dto.request.EnergyRecordRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.PersonResponse;
import dev.ener_track.com.demo.api.dto.response.relationResponse.EnergyRecordRelationResponse;
import dev.ener_track.com.demo.domain.entities.EnergyRecordEntity;
import dev.ener_track.com.demo.domain.entities.EnergyTypeEntity;
import dev.ener_track.com.demo.domain.respositories.EnergyRecordRepository;
import dev.ener_track.com.demo.domain.respositories.EnergyTypeRepository;
import dev.ener_track.com.demo.infracture.adstract_service.IEnergyRecordService;
import dev.ener_track.com.demo.infracture.client.UserFeing;
import dev.ener_track.com.demo.infracture.mapper.EnergyRecordMapper;
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
public class EnergyRecordService implements IEnergyRecordService {

    private EnergyRecordMapper energyRecordMapper;
    private EnergyRecordRepository energyRecordRepository;
    private EnergyTypeRepository energyTyperepository;
    private UserFeing userFeing;

    @Override
    public Page<EnergyRecordRelationResponse> getAll(int page, int size, SortType sortType) {

        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.energyRecordRepository.findAll(pagination).map(energyRecordMapper::toResponse);
    }

    @Override
    public EnergyRecordRelationResponse create(EnergyRecordRequest request) throws BadRequestException {

        //Validamos que exista el id mediante feing
        PersonResponse person = userFeing.getUserById(request.getUserId());
        if(person == null) throw new BadRequestException(ErrorMessages.IdNotFound(request.getUserId()));

        //Validamos que el tipo de energia exista
        Optional<EnergyTypeEntity> existingTypeEnergy = this.energyTyperepository.findByName(request.getEnergyTypeName());
        if (existingTypeEnergy.isEmpty()) throw new BadRequestException(ErrorMessages.NotFound(request.getEnergyTypeName()));

        EnergyRecordEntity newEnergyRecord = energyRecordMapper.toEntity(request);
        newEnergyRecord.setEnergyType(existingTypeEnergy.get());

        EnergyRecordEntity savedEntity = this.energyRecordRepository.save(newEnergyRecord);

       EnergyRecordRelationResponse response = this.energyRecordMapper.toResponse(savedEntity);
       response.setPerson(person);

       return response;
    }

    @Override
    public EnergyRecordRelationResponse getById(String id) throws BadRequestException {

        EnergyRecordEntity energyRecordEntity = this.find(id);

        System.out.println("##############" +
                energyRecordEntity +
                "############################");

       EnergyRecordRelationResponse response = this.energyRecordMapper.toResponse(energyRecordEntity);

       response.setPerson(this.userFeing.getUserById(energyRecordEntity.getUserId()));

        return response;
    }

    @Override
    public EnergyRecordRelationResponse update(String id, EnergyRecordRequest request) throws BadRequestException {

        Optional<EnergyTypeEntity> existingTypeEnergy = this.energyTyperepository.findByName(request.getEnergyTypeName());
        if (existingTypeEnergy.isEmpty()) throw new BadRequestException(ErrorMessages.NotFound(request.getEnergyTypeName()));

        EnergyRecordEntity energyRecord = this.find(id);

        this.energyRecordMapper.updateEnergyRecord(energyRecord, request);

        EnergyRecordEntity savedEntity = this.energyRecordRepository.save(energyRecord);
        
        energyRecord.setEnergyType(existingTypeEnergy.get());

        return this.energyRecordMapper.toResponse(savedEntity);
    }

    private EnergyRecordEntity find(String id) throws BadRequestException {

        return this.energyRecordRepository.findById(id).orElseThrow(
                () -> new BadRequestException(ErrorMessages.IdNotFound("Energy Record")));
    }
}
