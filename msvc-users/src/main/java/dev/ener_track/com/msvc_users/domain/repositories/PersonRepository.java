package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    PersonEntity findByDocument(String document);
}
