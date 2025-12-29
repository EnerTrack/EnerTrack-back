package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import org.apache.coyote.BadRequestException;

public interface CreateService <Requests, Response>{

    Response create(Requests request) throws BadRequestException;
}
