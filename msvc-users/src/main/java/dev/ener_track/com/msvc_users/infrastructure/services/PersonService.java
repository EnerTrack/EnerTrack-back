package dev.ener_track.com.msvc_users.infrastructure.services;

import dev.ener_track.com.msvc_users.api.dto.request.PersonRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.PersonRelationResponse;
import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import dev.ener_track.com.msvc_users.domain.repositories.DocumentTypeRepository;
import dev.ener_track.com.msvc_users.domain.repositories.PersonRepository;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IPersonService;
import dev.ener_track.com.msvc_users.infrastructure.mappers.PersonMapper;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import dev.ener_track.com.msvc_users.utils.exeptions.ErrorMessages;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final PersonMapper personMapper;


    @Override
    public Page<PersonRelationResponse> getAll(int page, int size, SortType sortType) {

        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.personRepository.findAll(pagination).map(personMapper::toResponse);
    }

    @Override
    public ValidateExistence existsByDocument(String document) {
       boolean exists = personRepository.findByDocument(document).isPresent();
        return new ValidateExistence(exists);
    }

    @Override
    public PersonRelationResponse create(PersonRequest request) throws BadRequestException {

        Optional<PersonEntity> personExisting = personRepository.findByDocument(request.getDocument());
        if (personExisting.isPresent()) {
            throw new BadRequestException(ErrorMessages.alreadyExists(request.getDocument()));
        }

        Optional<DocumentTypeEntity> documentType = documentTypeRepository.findByName(request.getDocumentType());
        if (documentType.isEmpty()) {
            throw new BadRequestException(ErrorMessages.IdNotFound("DocumentType"));
        }

        PersonEntity newPerson = personMapper.toEntity(request);
        newPerson.setDocumentType(documentType.get());

        PersonEntity savedPerson = personRepository.save(newPerson);

        return personMapper.toResponse(savedPerson);
    }

    @Override
    public PersonRelationResponse getById(String id) throws BadRequestException {

        PersonEntity personEntity = this.find(id);

        return personMapper.toResponse(personEntity);
    }

    @Override
    public PersonRelationResponse update(String id, PersonRequest request) throws BadRequestException {

        PersonEntity person = this.find(id);

        this.personMapper.updatePerson(person, request);
        PersonEntity savedPerson = personRepository.save(person);

        return this.personMapper.toResponse(savedPerson);
    }

    private PersonEntity find(String id) throws BadRequestException {
        return this.personRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.IdNotFound("person")));
    }

}
