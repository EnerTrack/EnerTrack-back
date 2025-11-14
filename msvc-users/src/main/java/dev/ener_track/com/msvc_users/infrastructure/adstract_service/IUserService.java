package dev.ener_track.com.msvc_users.infrastructure.adstract_service;

import dev.ener_track.com.msvc_users.api.dto.request.UserRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyEmail;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyLogin;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.UserRelationResponse;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.CreateService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.ReadAllService;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic.UpdateService;
import org.apache.coyote.BadRequestException;

public interface IUserService extends CreateService<UserRequest, UserRelationResponse>,
        UpdateService<UserRequest, UserRelationResponse, String>,
        ReadAllService<UserRelationResponse> {

    VerifyLogin verifyEmailAuth(VerifyEmail request) throws BadRequestException;
    public String FIELD_BY_SORT = "username";

}
