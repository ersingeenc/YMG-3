package com.ekipakis.task.service;

import com.ekipakis.task.dto.CreateTaskRequest;
import com.ekipakis.task.entity.Task;
import com.ekipakis.task.enums.TaskStatus;
import com.ekipakis.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final RestClient workspaceClient;

    public TaskService(
            TaskRepository taskRepository,
            RestClient.Builder restClientBuilder) {

        this.taskRepository = taskRepository;

        this.workspaceClient = restClientBuilder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public Task create(CreateTaskRequest request) {

        Boolean isMember = workspaceClient
                .get()
                .uri(
                        "/workspaces/{workspaceId}/members/{userId}/exists",
                        request.getWorkspaceId(),
                        request.getAssignedUserId()
                )
                .retrieve()
                .body(Boolean.class);

        if (!Boolean.TRUE.equals(isMember)) {
            throw new RuntimeException(
                    "Görev atanacak kullanıcı bu workspace'in üyesi değil."
            );
        }

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .workspaceId(request.getWorkspaceId())
                .assignedUserId(request.getAssignedUserId())
                .priority(request.getPriority())
                .deadline(request.getDeadline())
                .status(TaskStatus.TODO)
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Görev bulunamadı."
                        )
                );
    }

    public List<Task> getByWorkspace(
            Long workspaceId) {

        return taskRepository
                .findByWorkspaceId(workspaceId);
    }

    public List<Task> getByUser(
            Long userId) {

        return taskRepository
                .findByAssignedUserId(userId);
    }

    public List<Task> getUserTasksInWorkspace(
            Long workspaceId,
            Long userId) {

        return taskRepository
                .findByWorkspaceIdAndAssignedUserId(
                        workspaceId,
                        userId
                );
    }

    public Task updateStatus(
            Long id,
            TaskStatus status) {

        Task task = getById(id);

        task.setStatus(status);

        return taskRepository.save(task);
    }

    public void delete(Long id) {

        Task task = getById(id);

        taskRepository.delete(task);
    }
}