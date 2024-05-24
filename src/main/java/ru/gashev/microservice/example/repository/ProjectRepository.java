package ru.gashev.microservice.example.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gashev.microservice.example.entity.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findByParentId(Long parentId);


}
