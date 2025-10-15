package dev.ener_track.com.msvc_users.infrastructure.adstract_service.generic;

import java.util.Optional;

public interface ReadByIdService<Response, Id> {
    Optional<Response> getById(Id id);
}