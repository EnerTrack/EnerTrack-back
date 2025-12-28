package dev.ener_track.com.demo.api.controller;

import dev.ener_track.com.demo.api.dto.request.EnergyTypeRequest;
import dev.ener_track.com.demo.api.dto.response.basicResponse.EnergyTypeResponse;
import dev.ener_track.com.demo.infracture.adstract_service.IEnergyTypeService;
import dev.ener_track.com.demo.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy-type")
@AllArgsConstructor
public class EnergyTypeController {

    private IEnergyTypeService energyTypeService;

    @GetMapping
    public ResponseEntity<Page<EnergyTypeResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if(sortType==null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.energyTypeService.getAll(page, size, sortType));
    }

    @PostMapping
    public ResponseEntity<EnergyTypeResponse> create(
            @Validated @RequestBody EnergyTypeRequest resquest) throws BadRequestException {

        return ResponseEntity.ok(this.energyTypeService.create(resquest));
    }
}
