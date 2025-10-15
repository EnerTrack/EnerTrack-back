package dev.ener_track.com.msvc_users.domain.entities;

import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private Long phone;

    @Column(nullable = false, length = 20)
    private String document;

    private Date birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    private DocumentTypeEntity documentType;

}
