package com.devitiro.task.mappers;

import com.devitiro.task.domain.entities.TaskList;
import com.devitiro.task.domain.entities.dto.TaskListDto;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
