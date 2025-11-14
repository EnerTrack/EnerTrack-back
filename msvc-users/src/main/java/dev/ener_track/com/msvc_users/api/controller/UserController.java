package dev.ener_track.com.msvc_users.api.controller;

import dev.ener_track.com.msvc_users.api.dto.request.UserRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyEmail;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyLogin;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.UserRelationResponse;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IUserService;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserRelationResponse>> getAll(
            @Validated @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if(sortType==null) sortType = SortType.NONE;

        return  ResponseEntity.ok(this.userService.getAll(page -1, size, sortType));
    }

    @PostMapping(path = "/validate-users")
    public ResponseEntity<VerifyLogin> validateLogin(@RequestBody VerifyEmail request) throws BadRequestException {
        return ResponseEntity.ok(this.userService.verifyEmailAuth(request));
    }

    @PostMapping
    public ResponseEntity<UserRelationResponse> create(
            @Validated @RequestBody UserRequest request
    ) throws BadRequestException {

        return  ResponseEntity.ok(userService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRelationResponse> update(
            @Validated @RequestBody UserRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.userService.update(id, request));
    }

}
