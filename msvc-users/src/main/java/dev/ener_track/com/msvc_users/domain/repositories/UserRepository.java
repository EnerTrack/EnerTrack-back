package dev.ener_track.com.msvc_users.domain.repositories;

import dev.ener_track.com.msvc_users.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
