package ru.gashev.test.advenjineering.projectmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gashev.test.advenjineering.projectmanager.entity.Project;
import ru.gashev.test.advenjineering.projectmanager.repository.ProjectRepository;

import java.util.List;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskService taskService;

    public ProjectService(ProjectRepository projectRepository, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    public List<Project> getRootProjects() {
        return projectRepository.findByParentId(null);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }

    public List<Project> getSubprojects(Long parentId) {
        return projectRepository.findByParentId(parentId);
    }

    @Transactional
    public void saveProject(Project project) {
        log.info("Project save {}", project.toString());
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.findByParentId(id)
                .forEach(subproject -> this.deleteProject(subproject.getId()));
        taskService.getTasksByProjectId(id)
                .forEach(task -> taskService.deleteTask(task.getId()));
        projectRepository.delete(projectRepository.findById(id).get());
    }

}
