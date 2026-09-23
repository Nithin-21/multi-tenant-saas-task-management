package com.stask.saastask.task.service;

import com.stask.saastask.common.exception.ResourceNotFoundException;
import com.stask.saastask.organization.entity.Organization;
import com.stask.saastask.organization.repository.OrganizationRepository;
import com.stask.saastask.task.dto.CreateTaskRequest;
import com.stask.saastask.task.dto.TaskResponse;
import com.stask.saastask.task.dto.UpdateTaskRequest;
import com.stask.saastask.task.entity.Task;
import com.stask.saastask.task.entity.TaskStatus;
import com.stask.saastask.task.repository.TaskRepository;
import com.stask.saastask.user.entity.User;
import com.stask.saastask.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            OrganizationRepository organizationRepository,
            UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(CreateTaskRequest request) {

        Organization organization = organizationRepository
                .findById(request.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organization not found with id: "
                                + request.getOrganizationId()
                ));

        User createdBy = userRepository
                .findById(request.getCreatedById())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: "
                                + request.getCreatedById()
                ));

        User assignedTo = null;

        if (request.getAssignedToId() != null) {
            assignedTo = userRepository
                    .findById(request.getAssignedToId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Assigned user not found with id: "
                                    + request.getAssignedToId()
                    ));
        }

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setOrganization(organization);
        task.setCreatedBy(createdBy);
        task.setAssignedTo(assignedTo);
        task.setDueDate(request.getDueDate());

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with id: " + id
                ));

        return mapToResponse(task);
    }

    public Page<TaskResponse> getAllTasks(Pageable pageable) {

        return taskRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public TaskResponse updateTask(
            Long id,
            UpdateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with id: " + id
                ));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        if (request.getAssignedToId() != null) {
            User assignedTo = userRepository
                    .findById(request.getAssignedToId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Assigned user not found with id: "
                                    + request.getAssignedToId()
                    ));

            task.setAssignedTo(assignedTo);
        }

        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with id: " + id
                ));

        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getOrganization().getId(),
                task.getCreatedBy().getId(),
                task.getAssignedTo() != null
                        ? task.getAssignedTo().getId()
                        : null,
                task.getDueDate()
        );
    }
}