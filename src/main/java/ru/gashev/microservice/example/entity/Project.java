package ru.gashev.microservice.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@ManyToOne
    @JoinColumn(name = "parent_id")*/
    @Column(name = "parent_id")
    private Long parentId;

    /*@OneToMany(mappedBy = "parent")
    private Set<Project> subprojects;*/

    /*@OneToMany(mappedBy = "project")
    private Set<Task> tasks;*/

}
