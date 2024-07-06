package model;

import java.util.Objects;
import java.util.UUID;

public class Task {

    protected final String name;
    protected final String description;
    protected final UUID uuid;
    protected TaskStatus status;


    public Task(String name,  String description) {
        this(name, description, UUID.randomUUID(), TaskStatus.NEW);
    }

    public Task(String name,  String description, UUID uuid, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.uuid = uuid;
        this.status = status;
    }



    public UUID getUuid() {
        return uuid;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getClass() + "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(uuid, task.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}