package com.ekipakis.status.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStatusRequest {

    private Long userId;
    private Long workspaceId;
    private String yesterday;
    private String today;
    private String problem;
}