package com.nhnacademy.minidoorayuserapi.user.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public enum UserStatus {
        ACTIVITY, DORMANT_STATE, WITHDRAW
    }

    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }
}
