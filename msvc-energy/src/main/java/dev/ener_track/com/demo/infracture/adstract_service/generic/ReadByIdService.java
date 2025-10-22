package dev.ener_track.com.demo.infracture.adstract_service.generic;

import java.util.Optional;

public interface ReadByIdService<Response, Id> {
    Optional<Response> getById(Id id);
}