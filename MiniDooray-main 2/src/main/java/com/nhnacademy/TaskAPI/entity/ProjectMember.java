package com.nhnacademy.TaskAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "ProjectMembers")
public class ProjectMember {
    @EmbeddedId
    private PK pk;

//    @JsonBackReference
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @NoArgsConstructor
    @Getter
    @Setter
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "project_id")
        private Long projectId;
        @Column(name = "member_id")
        private Long memberId;
    }
}
