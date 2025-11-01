package dev.ener_track.com.msvc_data.domain.repositories;

import dev.ener_track.com.msvc_data.domain.entities.EnergyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyRecordRepository extends JpaRepository<EnergyRecordEntity, String> {

    @Query(value = """
    SELECT 
        et.name AS energy_type_name,
        e.country,
        e.year,
        e.generated_mwh,
        e.capacity_mwh,
        e.emission_reduction_tons,
        e.investment_usd,
        e.source,
        e.created_at,
        e.user_id,
        e.energy_type_id
    FROM (
        SELECT *,
               ROW_NUMBER() OVER (PARTITION BY e.energy_type_id ORDER BY e.generated_mwh DESC) AS rn
        FROM energy_record e
    ) e
    JOIN energy_type et ON e.energy_type_id = et.id
    WHERE e.rn <= 5
""", nativeQuery = true)
    List<Map<String, Object>> findTop5CountriesByEnergyType();


}
