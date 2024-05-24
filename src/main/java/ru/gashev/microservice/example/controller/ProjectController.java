package ru.gashev.microservice.example.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gashev.microservice.example.entity.Project;
import ru.gashev.microservice.example.entity.Task;
import ru.gashev.microservice.example.service.ProjectService;
import ru.gashev.microservice.example.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping
    public String getProjectsList(Model model) {
        List<Project> projects = projectService.getRootProjects();
        model.addAttribute("projects", projects);
        return "projects-list";
    }

    @GetMapping("/{id}")
    public String getProject(@PathVariable("id") long id, Model model, Session session) {
        Project project = projectService.getProject(id);
        List<Project> subprojects = projectService.getSubprojects(id);
        List<Task> tasks = taskService.getTasksByProjectId(id);
        model.addAttribute("project", project);
        model.addAttribute("parentId", project.getParentId());
        model.addAttribute("subprojects", subprojects);
        model.addAttribute("tasks", tasks);
        return "project";
    }

    @GetMapping("/new")
    public String getProjectCreationForm(@RequestParam(value = "parent", required = false) Long parentId,
                                         Model model) {
        model.addAttribute("parentId", parentId);
        return "project-new";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects/" + project.getId();
    }

    @GetMapping("/{id}/update")
    public String getProjectUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        Project project = projectService.getProject(id);
        model.addAttribute("oldName", project.getName());
        return "project-update";
    }

    @PostMapping("/{id}")
    public String updateProjectName(@PathVariable("id") long id,
                                    @RequestParam("name") String name) {
        Project project = projectService.getProject(id);
        project.setName(name);
        projectService.saveProject(project);
        return "redirect:/projects/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable("id") long id) {
        Long parentId = projectService.getProject(id).getParentId();
        projectService.deleteProject(id);
        if (parentId == null)
            return "redirect:/projects";
        else
            return "redirect:/projects/" + parentId;
    }

}
