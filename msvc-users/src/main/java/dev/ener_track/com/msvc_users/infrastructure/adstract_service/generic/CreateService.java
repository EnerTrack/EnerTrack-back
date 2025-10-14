package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import org.apache.coyote.BadRequestException;

public interface CreateService <Requets, Response>{

    Response create(Requets request) throws BadRequestException;
}
