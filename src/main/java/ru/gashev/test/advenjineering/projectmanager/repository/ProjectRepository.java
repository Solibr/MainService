package ru.gashev.test.advenjineering.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gashev.test.advenjineering.projectmanager.entity.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findByParentId(Long parentId);


}
