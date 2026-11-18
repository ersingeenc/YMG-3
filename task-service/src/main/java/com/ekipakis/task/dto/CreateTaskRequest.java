package com.ekipakis.task.dto;

import com.ekipakis.task.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskRequest {

    private String title;

    private String description;

    private Long workspaceId;

    private Long assignedUserId;

    private TaskPriority priority;

    private LocalDate deadline;
}