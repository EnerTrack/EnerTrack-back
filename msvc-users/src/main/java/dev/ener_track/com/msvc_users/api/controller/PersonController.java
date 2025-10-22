package dev.ener_track.com.msvc_users.api.controller;

import dev.ener_track.com.msvc_users.api.dto.request.PersonRequest;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.PersonRelationResponse;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IPersonService;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "person")
@AllArgsConstructor
public class PersonController {

    private IPersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonRelationResponse>> getAll(
            @Validated @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if(sortType==null) sortType = SortType.NONE;

        return  ResponseEntity.ok(personService.getAll(page -1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonRelationResponse> getById(
            @Validated @PathVariable String id
    ) throws BadRequestException {
        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PersonRelationResponse> create(
            @Validated @RequestBody PersonRequest request
    ) throws BadRequestException {

        return  ResponseEntity.ok(personService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonRelationResponse> update(
            @Validated @RequestBody PersonRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.personService.update(id, request));
    }

}
