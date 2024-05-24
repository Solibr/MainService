package ru.gashev.microservice.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "project_id")
    private Long projectId;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createTime;

    @Column(name = "status_update_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime statusUpdateTime;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

}
