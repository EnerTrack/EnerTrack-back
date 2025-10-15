package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import org.apache.coyote.BadRequestException;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request) throws BadRequestException;
}