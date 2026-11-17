package com.ekipakis.workspace.repository;

import com.ekipakis.workspace.entity.WorkspaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceMemberRepository
        extends JpaRepository<WorkspaceMember, Long> {

    List<WorkspaceMember> findByWorkspaceId(Long workspaceId);

    List<WorkspaceMember> findByUserId(Long userId);

    boolean existsByWorkspaceIdAndUserId(
            Long workspaceId,
            Long userId
    );

    void deleteByWorkspaceIdAndUserId(
            Long workspaceId,
            Long userId
    );
}