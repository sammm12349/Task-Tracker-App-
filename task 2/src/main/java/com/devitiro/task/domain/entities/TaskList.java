package com.devitiro.task.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "taskList",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Task> tasks = new ArrayList<>();

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    // No-arg constructor for JPA
    public TaskList() {
    }

    public TaskList(UUID id, String title, String description, List<Task> tasks, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks != null ? tasks : new ArrayList<>();
        this.created = created;
        this.updated = updated;
    }

    public TaskList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // --- Lifecycle timestamps ---
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.updated = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = LocalDateTime.now();
    }

    // --- Relationship helpers (keep both sides in sync) ---
    public void addTask(Task task) {
        if (task == null) return;
        tasks.add(task);
        task.setTaskList(this);
    }

    public void removeTask(Task task) {
        if (task == null) return;
        tasks.remove(task);
        task.setTaskList(null);
    }

    // --- Getters/Setters ---
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskList)) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}