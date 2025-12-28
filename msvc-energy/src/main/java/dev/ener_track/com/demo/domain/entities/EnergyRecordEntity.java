package dev.ener_track.com.demo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "energy_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, name = "generated_mwh")
    private double generatedMwh;

    @Column(nullable = false, name = "capacity_mwh")
    private double capacityMwh;

    @Column(nullable = false, name = "emission_reduction_tons")
    private double emissionReductionTons;

    @Column(nullable = false, name = "investment_usd")
    private double investmentUsd;

    @Column(nullable = false)
    private double source;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "energy_type_id", referencedColumnName = "id")
    private EnergyTypeEntity energyType;
}
