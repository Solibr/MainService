package ru.gashev.test.advenjineering.projectmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "project_id")
    private Long projectId;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "createtime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createTime;

    @Column(name = "status_update_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime statusUpdateTime;

    @Lob
    private String text;

}
