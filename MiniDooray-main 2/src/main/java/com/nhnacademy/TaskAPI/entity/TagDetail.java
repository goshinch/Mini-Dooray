package com.nhnacademy.TaskAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TagDetails")
@Getter
@Setter
@EqualsAndHashCode
public class TagDetail {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagDetailId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "tag_name")
    private String tagName;

    @OneToMany(mappedBy = "tagDetail", cascade = CascadeType.REMOVE)
    private List<Tag> tag;

}