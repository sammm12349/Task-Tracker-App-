package com.devitiro.task.services;

import com.devitiro.task.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList CreateTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID TaskListId, TaskList taskList);
    void deleteTaskList(UUID TaskListId);
}
