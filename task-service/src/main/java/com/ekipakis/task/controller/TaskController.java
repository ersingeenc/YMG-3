package com.ekipakis.task.controller;

import com.ekipakis.task.dto.CreateTaskRequest;
import com.ekipakis.task.dto.UpdateTaskStatusRequest;
import com.ekipakis.task.entity.Task;
import com.ekipakis.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(
            @RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(
                taskService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {

        return ResponseEntity.ok(
                taskService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                taskService.getById(id)
        );
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<Task>> getByWorkspace(
            @PathVariable Long workspaceId) {

        return ResponseEntity.ok(
                taskService.getByWorkspace(workspaceId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                taskService.getByUser(userId)
        );
    }

    @GetMapping("/workspace/{workspaceId}/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasksInWorkspace(
            @PathVariable Long workspaceId,
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                taskService.getUserTasksInWorkspace(
                        workspaceId,
                        userId
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateTaskStatusRequest request) {

        return ResponseEntity.ok(
                taskService.updateStatus(
                        id,
                        request.getStatus()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}