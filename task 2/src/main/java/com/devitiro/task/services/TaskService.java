package com.devitiro.task.services;

import com.devitiro.task.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> ListTasks(UUID TaskListId);
    Task createTask(UUID TaskLListId, Task task);
    Optional<Task> getTask(UUID TaskLListId, UUID TaskId);
    Task UpdateTask(UUID TaskLListId, UUID TaskId, Task task);
    void deleteTask(UUID TaskLListId, UUID TaskId);
}
