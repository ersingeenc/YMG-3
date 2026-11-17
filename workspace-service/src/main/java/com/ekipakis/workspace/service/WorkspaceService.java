package com.ekipakis.workspace.service;

import com.ekipakis.workspace.dto.AddMemberRequest;
import com.ekipakis.workspace.dto.CreateWorkspaceRequest;
import com.ekipakis.workspace.entity.Workspace;
import com.ekipakis.workspace.entity.WorkspaceMember;
import com.ekipakis.workspace.repository.WorkspaceMemberRepository;
import com.ekipakis.workspace.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMemberRepository memberRepository;

    public WorkspaceService(
            WorkspaceRepository workspaceRepository,
            WorkspaceMemberRepository memberRepository) {

        this.workspaceRepository = workspaceRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Workspace create(CreateWorkspaceRequest request) {

        Workspace workspace = Workspace.builder()
                .name(request.getName())
                .description(request.getDescription())
                .ownerId(request.getOwnerId())
                .build();

        Workspace savedWorkspace =
                workspaceRepository.save(workspace);

        WorkspaceMember ownerMember =
                WorkspaceMember.builder()
                        .workspaceId(savedWorkspace.getId())
                        .userId(request.getOwnerId())
                        .build();

        memberRepository.save(ownerMember);

        return savedWorkspace;
    }

    public List<Workspace> getAll() {
        return workspaceRepository.findAll();
    }

    public Workspace getById(Long id) {

        return workspaceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Workspace bulunamadı."
                        )
                );
    }

    public WorkspaceMember addMember(
            Long workspaceId,
            AddMemberRequest request) {

        getById(workspaceId);

        boolean exists =
                memberRepository
                        .existsByWorkspaceIdAndUserId(
                                workspaceId,
                                request.getUserId()
                        );

        if (exists) {
            throw new RuntimeException(
                    "Kullanıcı zaten workspace üyesi."
            );
        }

        WorkspaceMember member =
                WorkspaceMember.builder()
                        .workspaceId(workspaceId)
                        .userId(request.getUserId())
                        .build();

        return memberRepository.save(member);
    }

    public List<WorkspaceMember> getMembers(
            Long workspaceId) {

        getById(workspaceId);

        return memberRepository
                .findByWorkspaceId(workspaceId);
    }

    public List<Workspace> getUserWorkspaces(
            Long userId) {

        List<Long> workspaceIds =
                memberRepository
                        .findByUserId(userId)
                        .stream()
                        .map(WorkspaceMember::getWorkspaceId)
                        .toList();

        return workspaceRepository
                .findAllById(workspaceIds);
    }

    public Workspace update(
            Long id,
            Workspace newWorkspace) {

        Workspace workspace = getById(id);

        workspace.setName(newWorkspace.getName());
        workspace.setDescription(
                newWorkspace.getDescription()
        );

        return workspaceRepository.save(workspace);
    }

    @Transactional
    public void removeMember(
            Long workspaceId,
            Long userId) {

        Workspace workspace = getById(workspaceId);

        if (workspace.getOwnerId().equals(userId)) {
            throw new RuntimeException(
                    "Workspace sahibi workspace'den çıkarılamaz."
            );
        }

        memberRepository
                .deleteByWorkspaceIdAndUserId(
                        workspaceId,
                        userId
                );
    }
}