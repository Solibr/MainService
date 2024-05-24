package ru.gashev.microservice.example.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gashev.microservice.example.entity.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);
}
