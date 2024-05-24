package ru.gashev.test.advenjineering.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gashev.test.advenjineering.projectmanager.entity.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);
}
