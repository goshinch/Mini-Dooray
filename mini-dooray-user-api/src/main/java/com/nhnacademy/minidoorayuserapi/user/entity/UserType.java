package com.nhnacademy.minidoorayuserapi.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UserType")
public class UserType {
    @EmbeddedId
    private Pk pk;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('DEFAULT', 'GIT')")
    private Type type;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "user_id")
        private Long userId;
    }

    public enum Type {
        DEFAULT, GIT
    }
}
