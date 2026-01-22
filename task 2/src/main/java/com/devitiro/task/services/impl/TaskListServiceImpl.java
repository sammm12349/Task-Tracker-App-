package com.devitiro.task.services.impl;

import com.devitiro.task.domain.entities.TaskList;
import com.devitiro.task.repositrories.TaskListRepository;
import com.devitiro.task.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList CreateTaskList(TaskList taskList) {
        if(null != taskList.getId()) {
            throw new IllegalArgumentException("Task list already exists");
        }
        if(null == taskList.getId() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title should be present");
        }
        LocalDateTime now = LocalDateTime.now();
       return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now

        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID TaskListId, TaskList taskList) {
        if(null == taskList.getId()) {
            throw new IllegalArgumentException("Task list must have an ID");
        }
        if(Objects.equals(taskList.getId(), TaskListId)) {
            throw new IllegalArgumentException("Attempting to make this change is not permitted.");
        }
        TaskList existingTaskList = taskListRepository.findById(TaskListId).orElseThrow(()->
                new IllegalArgumentException("Task list does not exist"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID TaskListId) {
        taskListRepository.deleteById(TaskListId);
    }
}
