package dev.ener_track.com.demo.api.controller;

import dev.ener_track.com.demo.api.dto.request.EnergyRecordRequest;
import dev.ener_track.com.demo.api.dto.response.relationResponse.EnergyRecordRelationResponse;
import dev.ener_track.com.demo.infracture.adstract_service.IEnergyRecordService;
import dev.ener_track.com.demo.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "energy-record")
@AllArgsConstructor
public class EnergyRecordController {

    private IEnergyRecordService energyRecordService;


    @GetMapping
    public ResponseEntity<Page<EnergyRecordRelationResponse>> getAll(
            @Validated @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if(sortType==null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.energyRecordService.getAll(page -1, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyRecordRelationResponse> getById(
            @PathVariable("id") String id
    ) throws BadRequestException {

        return ResponseEntity.ok(this.energyRecordService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EnergyRecordRelationResponse> create(
            @Validated @RequestBody EnergyRecordRequest resquest
    ) throws BadRequestException {

        return ResponseEntity.ok(this.energyRecordService.create(resquest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyRecordRelationResponse> update(
            @PathVariable("id") String id,
            @Validated @RequestBody EnergyRecordRequest request
    ) throws BadRequestException {

        return ResponseEntity.ok(this.energyRecordService.update(id, request));
    }
}
