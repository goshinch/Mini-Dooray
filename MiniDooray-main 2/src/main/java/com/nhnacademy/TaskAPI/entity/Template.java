package com.nhnacademy.TaskAPI.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Templates")
public class Template {
    @EmbeddedId
    private PK pk;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "name")
    private String name;

    @Column(name = "explanation")
    private String explanation;
    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "project_id")
        private Long projectId;
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long templateId;
    }
}
