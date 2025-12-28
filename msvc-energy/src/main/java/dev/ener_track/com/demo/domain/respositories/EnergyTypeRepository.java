package dev.ener_track.com.demo.domain.respositories;

import dev.ener_track.com.demo.domain.entities.EnergyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EnergyTypeRepository extends JpaRepository<EnergyTypeEntity, String> {

    Optional<EnergyTypeEntity> findByName(String name);
}
