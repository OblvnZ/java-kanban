package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import model.*;

public class TaskManager {

    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Epic> epics = new HashMap<>();
    private final HashMap<UUID, Subtask> subtasks = new HashMap<>();

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateTaskStatus(epic);
        }
        subtasks.clear();
    }

    public void removeEpics() {
        epics.clear();
        subtasks.clear();
    }

    public Task getTaskById(UUID uuid) {
        if (tasks.containsKey(uuid)) {
            return tasks.get(uuid);
        } else if (epics.containsKey(uuid)) {
            return epics.get(uuid);
        } else if (subtasks.containsKey(uuid)) {
            return subtasks.get(uuid);
        }
        return null;
    }

    public void addTask(Task task) {
        if (task.getClass() == Task.class) tasks.put(task.getUuid(), task);
        if (task.getClass() == Subtask.class) {
            updateTaskStatus(((Subtask) task).getEpic());
            subtasks.put(task.getUuid(), (Subtask) task);
        }
        if (task.getClass() == Epic.class) epics.put(task.getUuid(), (Epic) task);
    }

    public void updateTask(Task task) {
        if (task.getClass() == Task.class) tasks.put(task.getUuid(), task);
        if (task.getClass() == Epic.class) epics.put(task.getUuid(), (Epic) task);
        if (task.getClass() == Subtask.class) {
            subtasks.put(task.getUuid(), (Subtask) task);
            updateTaskStatus(((Subtask) task).getEpic());
        }
    }

    public void removeTaskById(UUID uuid) {
        if (getTaskById(uuid).getClass() == Epic.class) {
            Epic epic = (Epic) getTaskById(uuid);
            for (Subtask subtask : getSubtasksByEpic(epic)) {
                if (subtasks.containsValue(subtask)) subtasks.remove(subtask.getUuid());
            }
            epics.remove(uuid);
        } else if (getTaskById(uuid).getClass() == Subtask.class) {
            Subtask subtaskToRemove = (Subtask) getTaskById(uuid);
            subtasks.remove(uuid);
            updateTaskStatus(subtaskToRemove.getEpic());
        } else if (getTaskById(uuid).getClass() == Task.class) {
            tasks.remove(uuid);
        }
    }

    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public void updateTaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        Epic epic = subtask.getEpic();
        updateTaskStatus(epic);
    }

    public void updateTaskStatus(Epic epic) {
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        for (int i = 0; i < subtasks.size() - 1; i++) {
            if (subtasks.get(i).getStatus() != subtasks.get(i + 1).getStatus()) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }
        TaskStatus newStatus = subtasks.getFirst().getStatus();
        epic.setStatus(newStatus);
    }

    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
    }
}