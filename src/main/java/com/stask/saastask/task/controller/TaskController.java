package com.stask.saastask.task.controller;

import com.stask.saastask.task.dto.CreateTaskRequest;
import com.stask.saastask.task.dto.TaskResponse;
import com.stask.saastask.task.dto.UpdateTaskRequest;
import com.stask.saastask.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        return taskService.createTask(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(
            @PathVariable Long id) {

        return taskService.getTaskById(id);
    }

    @GetMapping
    public Page<TaskResponse> getAllTasks(Pageable pageable) {

        return taskService.getAllTasks(pageable);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request) {

        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);
    }
}