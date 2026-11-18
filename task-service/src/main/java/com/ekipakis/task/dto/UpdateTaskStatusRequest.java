package com.ekipakis.task.dto;

import com.ekipakis.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    private TaskStatus status;
}