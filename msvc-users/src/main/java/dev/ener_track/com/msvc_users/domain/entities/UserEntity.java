package dev.ener_track.com.msvc_users.domain.entities;

import dev.ener_track.com.msvc_users.utils.emuns.Role;
import dev.ener_track.com.msvc_users.utils.emuns.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column( nullable = false)
    private String username;

    @Column( nullable = false)
    private String password;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;
}
