package ru.gashev.microservice.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gashev.microservice.example.entity.Task;
import ru.gashev.microservice.example.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable("id") long id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/new")
    public String getTaskCreationForm(Model model,
                                      @RequestParam("project-id") Long projectId) {
        model.addAttribute("projectId", projectId);
        return "task-new";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.getTask(id));
        return "task-update";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {

        Long id = taskService.createTask(task);
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable("id") long id, @ModelAttribute Task task) {
        id = taskService.updateTask(task);
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") long id) {
        Long projectId = taskService.getTask(id).getProjectId();
        taskService.deleteTask(id);
        return "redirect:/projects/" + projectId;
    }

}
