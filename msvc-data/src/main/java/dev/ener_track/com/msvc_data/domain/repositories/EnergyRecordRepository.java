package dev.ener_track.com.msvc_data.domain.repositories;

import dev.ener_track.com.msvc_data.api.dto.response.group_response.EnergyTypeMostUseResponse;
import dev.ener_track.com.msvc_data.domain.entities.EnergyRecordEntity;
import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyRecordRepository extends JpaRepository<EnergyRecordEntity, String> {

    @Query(value = """
                WITH country_totals AS (
                    SELECT
                        e.country,
                        SUM(e.generated_mwh) AS total_country_mwh
                    FROM energy_record e
                    GROUP BY e.country
                    ORDER BY total_country_mwh DESC
                    LIMIT 5
                ),
                ranked_energy AS (
                    SELECT
                        e.country,
                        et.id AS energy_type_id,
                        et.name AS energy_type_name,
                        SUM(e.generated_mwh) AS total_generated_mwh,
                        ROW_NUMBER() OVER (PARTITION BY e.country ORDER BY SUM(e.generated_mwh) DESC) AS rn
                    FROM energy_record e
                    JOIN energy_type et ON e.energy_type_id = et.id
                    WHERE e.country IN (SELECT country FROM country_totals)
                    GROUP BY e.country, et.id, et.name
                )
                SELECT *
                FROM ranked_energy
                WHERE rn <= 5
                ORDER BY country, total_generated_mwh DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findTop5CountriesAndTheirTop5EnergyTypes();

    @Query(value = """
                SELECT
                    e.energy_type_id AS energyTypeId,
                    et.name AS energyTypeName,
                    COUNT(e.id) AS usageCount
                FROM energy_record e
                JOIN energy_type et ON e.energy_type_id = et.id
                GROUP BY e.energy_type_id, et.name
                ORDER BY usageCount DESC
            """, nativeQuery = true)
    List<EnergyTypeMostUseResponse> findAllEnergyTypesUsage();
    /********************************************************************************************************************* */

@Query(value = """
    SELECT 
        e.country,
        e.year,
        e.emission_reduction_tons AS emissionReductionTons,
        prev.total_emission AS previousYearEmission
    FROM energy_record e
    LEFT JOIN (
        SELECT country, year, SUM(emission_reduction_tons) AS total_emission
        FROM energy_record
        GROUP BY country, year
    ) prev
    ON prev.country = e.country AND prev.year = e.year - 1
    WHERE e.year = (SELECT MAX(year) FROM energy_record)
    LIMIT 3 OFFSET :offset
    """, nativeQuery = true)
List<Map<String, Object>> findRandomEmissionRecordsFromLastYearWithPrevious(@Param("offset") int offset);

@Query(value = "SELECT COUNT(*) FROM energy_record WHERE year = (SELECT MAX(year) FROM energy_record)", nativeQuery = true)
int countByLastYear();

}
