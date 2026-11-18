package com.ekipakis.task.repository;

import com.ekipakis.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    List<Task> findByWorkspaceId(Long workspaceId);

    List<Task> findByAssignedUserId(Long assignedUserId);

    List<Task> findByWorkspaceIdAndAssignedUserId(
            Long workspaceId,
            Long assignedUserId
    );
}