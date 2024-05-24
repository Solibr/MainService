package ru.gashev.microservice.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gashev.microservice.example.entity.Task;
import ru.gashev.microservice.example.entity.TaskStatus;
import ru.gashev.microservice.example.repository.TaskRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Long createTask(Task task) {
        log.info("Task save: {}", task.getName());
        task.setStatus(TaskStatus.NEW);
        task.setCreateTime(ZonedDateTime.now());
        task.setStatusUpdateTime(ZonedDateTime.now());
        return taskRepository.save(task).getId();
    }

    public Long updateTask(Task updatedTask) {
        log.info("Task update: {}", updatedTask.getName());
        Task persistedTask = taskRepository.findById(updatedTask.getId()).get();

        persistedTask.setName(updatedTask.getName());
        persistedTask.setType(updatedTask.getType());
        persistedTask.setText(updatedTask.getText());
        if (persistedTask.getStatus() != updatedTask.getStatus())
            persistedTask.setStatusUpdateTime(ZonedDateTime.now());
        persistedTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(persistedTask).getId();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
