package dev.ener_track.com.msvc_users.domain.entities;

import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "document_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}
