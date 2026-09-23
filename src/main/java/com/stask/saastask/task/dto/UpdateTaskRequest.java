package com.stask.saastask.task.dto;

import com.stask.saastask.task.entity.TaskPriority;
import com.stask.saastask.task.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class UpdateTaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private Long assignedToId;

    private LocalDate dueDate;

    public UpdateTaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}