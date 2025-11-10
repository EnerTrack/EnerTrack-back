package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.ValidateExistence;
import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    Optional<PersonEntity> findByDocument(String document);
}
