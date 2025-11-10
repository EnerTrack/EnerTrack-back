package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, String> {
    Optional<DocumentTypeEntity> findByName(String name);
}
