package com.ekipakis.workspace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkspaceRequest {

    private String name;
    private String description;
    private Long ownerId;
}