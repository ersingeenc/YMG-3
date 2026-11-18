package com.ekipakis.status.repository;

import com.ekipakis.status.entity.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkStatusRepository
        extends JpaRepository<WorkStatus, Long> {

    List<WorkStatus> findByUserId(Long userId);

    List<WorkStatus> findByWorkspaceId(Long workspaceId);

    List<WorkStatus> findByWorkspaceIdAndUserId(
            Long workspaceId,
            Long userId
    );
}