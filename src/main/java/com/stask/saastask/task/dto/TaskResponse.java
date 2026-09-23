package com.stask.saastask.task.dto;

import com.stask.saastask.task.entity.TaskPriority;
import com.stask.saastask.task.entity.TaskStatus;

import java.time.LocalDate;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long organizationId;
    private Long createdById;
    private Long assignedToId;
    private LocalDate dueDate;

    public TaskResponse() {
    }

    public TaskResponse(
            Long id,
            String title,
            String description,
            TaskStatus status,
            TaskPriority priority,
            Long organizationId,
            Long createdById,
            Long assignedToId,
            LocalDate dueDate) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.organizationId = organizationId;
        this.createdById = createdById;
        this.assignedToId = assignedToId;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}