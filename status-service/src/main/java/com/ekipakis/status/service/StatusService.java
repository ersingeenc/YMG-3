package com.ekipakis.status.service;

import com.ekipakis.status.dto.CreateStatusRequest;
import com.ekipakis.status.entity.WorkStatus;
import com.ekipakis.status.repository.WorkStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final WorkStatusRepository repository;

    public StatusService(WorkStatusRepository repository) {
        this.repository = repository;
    }

    public WorkStatus create(CreateStatusRequest request) {

        WorkStatus status = WorkStatus.builder()
                .userId(request.getUserId())
                .workspaceId(request.getWorkspaceId())
                .yesterday(request.getYesterday())
                .today(request.getToday())
                .problem(request.getProblem())
                .build();

        return repository.save(status);
    }

    public List<WorkStatus> getAll() {
        return repository.findAll();
    }

    public WorkStatus getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Durum bulunamadı.")
                );
    }

    public List<WorkStatus> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<WorkStatus> getByWorkspace(Long workspaceId) {
        return repository.findByWorkspaceId(workspaceId);
    }

    public List<WorkStatus> getUserStatusesInWorkspace(
            Long workspaceId,
            Long userId) {

        return repository
                .findByWorkspaceIdAndUserId(
                        workspaceId,
                        userId
                );
    }

    public void delete(Long id) {

        WorkStatus status = getById(id);

        repository.delete(status);
    }
}