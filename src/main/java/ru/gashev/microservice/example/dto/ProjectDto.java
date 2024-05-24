package ru.gashev.microservice.example.dto;

import lombok.Data;

@Data
public class ProjectDto {

    private Long id;

    private String name;

    private Long parentId;
}
