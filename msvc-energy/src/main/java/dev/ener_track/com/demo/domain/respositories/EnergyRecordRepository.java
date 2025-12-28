package dev.ener_track.com.demo.domain.respositories;


import dev.ener_track.com.demo.domain.entities.EnergyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyRecordRepository extends JpaRepository<EnergyRecordEntity, String> {
}
