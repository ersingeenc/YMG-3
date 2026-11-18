package com.ekipakis.status.controller;

import com.ekipakis.status.dto.CreateStatusRequest;
import com.ekipakis.status.entity.WorkStatus;
import com.ekipakis.status.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<WorkStatus> create(
            @RequestBody CreateStatusRequest request) {

        return ResponseEntity.ok(
                statusService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<WorkStatus>> getAll() {

        return ResponseEntity.ok(
                statusService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkStatus> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                statusService.getById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkStatus>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                statusService.getByUser(userId)
        );
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<WorkStatus>> getByWorkspace(
            @PathVariable Long workspaceId) {

        return ResponseEntity.ok(
                statusService.getByWorkspace(workspaceId)
        );
    }

    @GetMapping("/workspace/{workspaceId}/user/{userId}")
    public ResponseEntity<List<WorkStatus>> getUserStatuses(
            @PathVariable Long workspaceId,
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                statusService.getUserStatusesInWorkspace(
                        workspaceId,
                        userId
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        statusService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}