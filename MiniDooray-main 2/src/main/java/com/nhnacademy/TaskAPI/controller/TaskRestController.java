package com.nhnacademy.TaskAPI.controller;

import com.nhnacademy.TaskAPI.dto.task.TaskDto;
import com.nhnacademy.TaskAPI.dto.task.TaskRequest;
import com.nhnacademy.TaskAPI.dto.task.TaskResponse;
import com.nhnacademy.TaskAPI.service.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return taskService.getTask(taskId);
    }

    @PostMapping("/new")
    public TaskDto createTask(@RequestParam("projectId") Long projectId, @RequestBody TaskRequest taskRequest) {
        return taskService.createTask(projectId, taskRequest);
    }

    @PatchMapping("/update")
    public TaskDto updateTask(@RequestParam("taskId") Long taskId, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(taskId, taskRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProject(@RequestParam("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }
}
