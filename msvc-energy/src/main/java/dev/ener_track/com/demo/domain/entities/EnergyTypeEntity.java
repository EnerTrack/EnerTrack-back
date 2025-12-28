package dev.ener_track.com.demo.domain.entities;

import dev.ener_track.com.demo.utils.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "energy_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnergyTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
