package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    Optional<PersonEntity> findByDocument(String document);
    @Query("""
    SELECT p
    FROM person p
    LEFT JOIN FETCH p.user u
    WHERE p.email = :email
    """)
    Optional<PersonEntity> findByEmailWithUser(String email);
}
