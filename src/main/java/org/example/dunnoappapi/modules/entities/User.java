package org.example.dunnoappapi.modules.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

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
    //@JsonIgnoreProperties(value = { "contacts","invoices" })
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    //@JsonIgnoreProperties(value = { "contacts","invoices" })
    @JoinColumn(name = "membership_id")
    private Membership membership;

    private Date subscription_start;

    private Date subscription_end;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false)
    private String timestamp;
}
