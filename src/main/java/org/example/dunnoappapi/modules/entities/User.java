package org.example.dunnoappapi.modules.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String email;

    private String password;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "membership_id")
    private Membership membership;

    private Date subscription_start;

    private Date subscription_end;

    private boolean trial;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false)
    private String timestamp;
}
