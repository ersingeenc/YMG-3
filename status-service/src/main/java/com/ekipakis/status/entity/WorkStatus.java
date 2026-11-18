package com.ekipakis.status.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "work_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long workspaceId;

    private String yesterday;

    private String today;

    private String problem;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}

