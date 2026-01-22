package com.devitiro.task.domain.entities.dto;

import com.devitiro.task.domain.entities.TaskPriority;
import com.devitiro.task.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(  UUID id,
                        String title,
                        String description,
                        LocalDateTime dueDate,
                        TaskPriority priority,
                        TaskStatus status
) {


}
