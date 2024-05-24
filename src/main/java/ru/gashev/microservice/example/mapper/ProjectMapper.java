package ru.gashev.microservice.example.mapper;

import org.mapstruct.Mapper;
import ru.gashev.microservice.example.dto.ProjectDto;
import ru.gashev.microservice.example.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends Mappable<Project, ProjectDto> {
}
