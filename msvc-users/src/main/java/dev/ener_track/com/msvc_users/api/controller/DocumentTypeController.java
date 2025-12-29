package dev.ener_track.com.msvc_users.api.controller;

import dev.ener_track.com.msvc_users.api.dto.request.DocumentTypeRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.DocumentTypeResponse;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IDocumentTypeService;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import lombok.AllArgsConstructor;


import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "document-type")
@AllArgsConstructor
public class DocumentTypeController {

    private IDocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<Page<DocumentTypeResponse>> getAll(
            @Validated @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if(sortType==null) sortType = SortType.NONE;

        return  ResponseEntity.ok(documentTypeService.getAll(page -1, size, sortType));
    }

    @GetMapping("/validate-name")
    public ResponseEntity<ValidateExistence> ValidatedDocumentType(
            @RequestParam String name
    )  {
        return ResponseEntity.ok(this.documentTypeService.existsByName(name));
    }

    @PostMapping
    public ResponseEntity<DocumentTypeResponse> create(
            @Validated @RequestBody DocumentTypeRequest request
    ) throws BadRequestException {

        return  ResponseEntity.ok(documentTypeService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DocumentTypeResponse> update(
            @Validated @RequestBody DocumentTypeRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.documentTypeService.update(id, request));

    }


}
