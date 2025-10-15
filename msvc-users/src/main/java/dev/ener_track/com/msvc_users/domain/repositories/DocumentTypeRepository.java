package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.domain.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, String> {
    DocumentTypeEntity findByName(String name);
}
