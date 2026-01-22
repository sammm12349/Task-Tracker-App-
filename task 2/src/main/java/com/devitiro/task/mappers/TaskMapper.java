package com.devitiro.task.mappers;

import com.devitiro.task.domain.entities.Task;

import com.devitiro.task.domain.entities.dto.TaskDto;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);  // Fixed: TaskDTO not TaskDTo
}