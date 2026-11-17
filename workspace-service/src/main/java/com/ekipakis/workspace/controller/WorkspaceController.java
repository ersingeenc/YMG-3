package com.ekipakis.workspace.controller;

import com.ekipakis.workspace.dto.AddMemberRequest;
import com.ekipakis.workspace.dto.CreateWorkspaceRequest;
import com.ekipakis.workspace.entity.Workspace;
import com.ekipakis.workspace.entity.WorkspaceMember;
import com.ekipakis.workspace.service.WorkspaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(
            WorkspaceService workspaceService) {

        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<Workspace> create(
            @RequestBody CreateWorkspaceRequest request) {

        return ResponseEntity.ok(
                workspaceService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<Workspace>> getAll() {

        return ResponseEntity.ok(
                workspaceService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                workspaceService.getById(id)
        );
    }

    @PostMapping("/{workspaceId}/members")
    public ResponseEntity<WorkspaceMember> addMember(
            @PathVariable Long workspaceId,
            @RequestBody AddMemberRequest request) {

        return ResponseEntity.ok(
                workspaceService.addMember(
                        workspaceId,
                        request
                )
        );
    }

    @GetMapping("/{workspaceId}/members")
    public ResponseEntity<List<WorkspaceMember>> getMembers(
            @PathVariable Long workspaceId) {

        return ResponseEntity.ok(
                workspaceService.getMembers(workspaceId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workspace>> getUserWorkspaces(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                workspaceService.getUserWorkspaces(userId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workspace> update(
            @PathVariable Long id,
            @RequestBody Workspace workspace) {

        return ResponseEntity.ok(
                workspaceService.update(id, workspace)
        );
    }

    @DeleteMapping("/{workspaceId}/members/{userId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long workspaceId,
            @PathVariable Long userId) {

        workspaceService.removeMember(
                workspaceId,
                userId
        );

        return ResponseEntity.noContent().build();
    }
}