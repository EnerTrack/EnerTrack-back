package dev.ener_track.com.msvc_users.infrastructure.services;

import dev.ener_track.com.msvc_users.api.dto.request.DocumentTypeRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.DocumentTypeResponse;
import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import dev.ener_track.com.msvc_users.domain.repositories.DocumentTypeRepository;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IDocumentTypeService;
import dev.ener_track.com.msvc_users.infrastructure.mappers.DocumentTypeMapper;
import dev.ener_track.com.msvc_users.utils.exeptions.ErrorMesasges;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentTypeSerive implements IDocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public Page<DocumentTypeResponse> getAll(int page, int size, SortType sortType) {

        if (page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.documentTypeRepository.findAll(pagination).map(this.documentTypeMapper::toResponse);
    }


    @Override
    public DocumentTypeResponse create(DocumentTypeRequest request) throws BadRequestException {

        DocumentTypeEntity documentTypeExisting = documentTypeRepository.findByName(request.getName());

        if(documentTypeExisting != null) throw new BadRequestException(ErrorMesasges.alreadyExists(request.getName()));

        DocumentTypeEntity newDocumentType = this.documentTypeMapper.toEntity(request);
        DocumentTypeEntity savedDocumentType = documentTypeRepository.save(newDocumentType);

        return this.documentTypeMapper.toResponse(savedDocumentType);
    }

    @Override
    public DocumentTypeResponse update(String id, DocumentTypeRequest request) throws BadRequestException {

        DocumentTypeEntity documentType = this.find(id);

        this.documentTypeMapper.updateDocumentType(documentType, request);
        DocumentTypeEntity savedDocumentType = this.documentTypeRepository.save(documentType);

        return this.documentTypeMapper.toResponse(savedDocumentType);
    }

    /*
    *
    * */
    private DocumentTypeEntity find(String id) throws BadRequestException {

        return this.documentTypeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMesasges.IdNotFound("DoumentType")));
    }

}
