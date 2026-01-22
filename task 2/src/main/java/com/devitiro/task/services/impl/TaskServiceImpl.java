package com.devitiro.task.services.impl;

import com.devitiro.task.domain.entities.Task;
import com.devitiro.task.domain.entities.TaskList;
import com.devitiro.task.domain.entities.TaskPriority;
import com.devitiro.task.domain.entities.TaskStatus;
import com.devitiro.task.mappers.TaskMapper;
import com.devitiro.task.repositrories.TaskListRepository;
import com.devitiro.task.repositrories.TaskRepository;
import com.devitiro.task.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> ListTasks(UUID TaskListId) {
        return taskRepository.findByTaskListId(TaskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID TaskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task id is already set");
        }
        if(null ==task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title is empty");
        }

       TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
               .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

       TaskList taskList = taskListRepository.findById(TaskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID!"));

        LocalDateTime now = LocalDateTime.now();
       Task taskToSave = new Task(
               null,
               task.getTitle(),
               task.getDescription(),
               task.getDueDate(),
               taskStatus,
               taskPriority,
               taskList,
               now,
               now

       );

       return taskRepository.save(taskToSave);

    }

    @Override
    public Optional<Task> getTask(UUID TaskLListId, UUID TaskId) {
        return taskRepository.findByTaskListIdAndId(TaskLListId, TaskId);
    }

    @Transactional
    @Override
    public Task UpdateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()){
            throw new IllegalArgumentException("Task must have Id");

        }
        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task ID does not match");
        }
        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task must have a valid priority");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have a valid status");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task was not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);

    }

    @Transactional
    @Override
    public void deleteTask(UUID TaskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(TaskListId, taskId);
    }
}
