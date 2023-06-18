package com.nhnacademy.TaskAPI.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "state", columnDefinition = "ENUM('TODO','WORKING','COMPLETE') DEFAULT 'TODO'")
    @Enumerated(EnumType.STRING)
    private Task.State state;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task")
    private List<Tag> tag;

    public enum State {
        TODO, WORKING, COMPLETE
    }
}
